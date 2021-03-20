package com.carnnjoh.poedatatool.db.implementation;

import com.carnnjoh.poedatatool.db.dao.ItemFilterTypeDAO;
import com.carnnjoh.poedatatool.db.dao.ItemFilterTypeSubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.model.ItemFilterType;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.model.ItemFilterTypeSubscription;
import com.carnnjoh.poedatatool.db.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemFilterTypeSubscriptionImpl implements ItemFilterTypeSubscriptionDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemFilterTypeSubscriptionImpl.class);

	@Autowired
	ItemFilterTypeDAO itemFilterTypeDAO;

	@Autowired
	SubscriptionDAO subscriptionDAO;

	private NamedParameterJdbcTemplate template;

	public ItemFilterTypeSubscriptionImpl(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Result saveRelation(int subscriptionPk, List<ItemFilterType> itemFilterTypes) {
		return Utils.tryUpdate(() -> {

			List<Integer> keyList = new ArrayList<>();
			int updatedRows = 0;

			for (ItemFilterType itemFilterType : itemFilterTypes) {

				MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("subscription_fk", subscriptionPk)
					.addValue("itemFilterType_fk", itemFilterType.getPk());

				KeyHolder keyHolder = new GeneratedKeyHolder();

				int updatedRow = template.update(
					"Insert Into ItemFilterType_Subscription" +
						" ( subscription_fk, itemFilterType_fk) " +
						"values ( :subscription_fk, :itemFilterType_fk)",
					params,
					keyHolder
				);

				LOGGER.info(String.format("Saved connection subscription_fk: %d, itemFilterType_fk: %d",
					subscriptionPk,
					itemFilterType.getPk()
				));

				updatedRows += updatedRow;
				keyList.add(keyHolder.getKey().intValue());
			}

			return (updatedRows > 0)
				? Utils.getCreateResultList(keyList)
				: new FailedResult();
		});
	}

	///This should also be optimized. it performs 1 + 1 + X DB operations.
	@Override
	public Result saveSubscription(Subscription subscription) {
		return Utils.tryUpdate(() -> {

			Result subscriptionSaveResult = subscriptionDAO.save(subscription);

			CreateSuccessResult createSuccessResult = (subscriptionSaveResult instanceof CreateSuccessResult)
				? (CreateSuccessResult) subscriptionSaveResult
				: null;

			if (createSuccessResult == null) {
				return new FailedResult();
			}

			List<String> itemFilterNames = subscription
				.getItemFilterTypes()
				.stream()
				.map(ItemFilterType::getName)
				.collect(Collectors.toList());

			List<ItemFilterType> ItemFilterTypeWithIds = itemFilterTypeDAO.fetchByName(itemFilterNames);

			Result  saveRelationResult = saveRelation(createSuccessResult.getPk(), ItemFilterTypeWithIds);

			return (saveRelationResult instanceof CreateSuccessResultList)
				? subscriptionSaveResult
				: new FailedResult();
		});
	}

	@Override
	public Result updateSubscription(Subscription subscription) {
		return Utils.tryUpdate(() -> {

			Result subscriptionUpdateResult = subscriptionDAO.update(subscription);

			UpdateSuccessResult updateSuccessResult = (subscriptionUpdateResult instanceof UpdateSuccessResult)
				? (UpdateSuccessResult) subscriptionUpdateResult
				: null;

			if (updateSuccessResult == null) {
				return new FailedResult();
			}

			return updateRelation(subscription.getPk(), subscription.getItemFilterTypes());
		});
	}

	//TODO: optimize this ...
	@Override
	public Result updateRelation(int subscriptionPk, List<ItemFilterType> itemFilterTypes) {
		return Utils.tryUpdate(() -> {

			Result deleteRelationResult = deleteRelation(subscriptionPk);

			if (deleteRelationResult instanceof DeleteSuccessResult) {
				return saveRelation(subscriptionPk, itemFilterTypes);
			}

			return new FailedResult();
		});
	}

	@Override
	public Result deleteRelation(int subscriptionPk) {
		return Utils.tryUpdate(() -> {

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("subscription_fk", subscriptionPk);
			int rowsUpdated = template.update(
				"delete from ItemFilterType_Subscription where subscription_fk = :subscription_fk",
				params);
			return (rowsUpdated > 0)
				? new DeleteSuccessResult()
				: new FailedResult();
		});

	}

	@Override
	public Result deleteSubscription(int subscriptionPk) {
		return Utils.tryUpdate(() -> {

			Result deleteRelationResult = deleteRelation(subscriptionPk);

			if (deleteRelationResult instanceof DeleteSuccessResult) {
				return subscriptionDAO.deleteByPk(subscriptionPk);
			}
			return new FailedResult();
		});
	}

	//TODO: optimize this ...
	public List<ItemFilterTypeSubscription> fetchRelationBySubscription(int subscriptionFk) {
		return Utils.tryGet(() -> {

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("subscription_fk", subscriptionFk);

			return template.query(
				"Select * from ItemFilterType_Subscription where subscription_fk = :subscription_fk",
				params,
				rowMapper
			);
		});
	}

	//TODO: optimize this ...
	public Subscription getSubscription(int subscriptionPk) {
		return Utils.tryGet(() -> {

			Subscription subscription = subscriptionDAO.fetch(subscriptionPk);

			List<Integer> itemFilterPks = fetchRelationBySubscription(subscriptionPk)
				.stream()
				.map(ItemFilterTypeSubscription::getItemFilterTypeFk)
				.collect(Collectors.toList());

			itemFilterPks.forEach(pk -> System.out.println("itemFilterType foregin key: " + pk));

			List<ItemFilterType> itemFilterTypes = itemFilterTypeDAO.fetchByIds(itemFilterPks);

			subscription.setItemFilterTypes(itemFilterTypes);
			return subscription;
		});
	}

	//TODO: optimize this ...
	public List<Subscription> getAllSubscription() {
		return Utils.tryGet(() -> {
			List<Subscription> subscriptions = subscriptionDAO.fetchAll();

			for (Subscription subscription : subscriptions) {
				List<Integer> relations = fetchRelationBySubscription(subscription.getPk())
					.stream()
					.map(ItemFilterTypeSubscription::getItemFilterTypeFk)
					.collect(Collectors.toList());
				subscription.setItemFilterTypes(itemFilterTypeDAO.fetchByIds(relations));
			}

			return subscriptions;
		});
	}

	private final RowMapper<ItemFilterTypeSubscription> rowMapper = ((rs, rowNum) ->
		Utils.tryGet(() ->
			new ItemFilterTypeSubscription(
				rs.getInt("pk"),
				rs.getInt("subscription_fk"),
				rs.getInt("ItemFilterType_fk")
			)
		));
}
