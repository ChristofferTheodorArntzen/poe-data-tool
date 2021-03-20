package com.carnnjoh.poedatatool.db.dao;

import com.carnnjoh.poedatatool.db.model.ItemFilterType;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.utils.Result;

import java.util.List;

public interface ItemFilterTypeSubscriptionDAO {

	//TODO: impl. these methods.
	// remove DAO interface from all DAO's.
	// Make a conclusion of how to retrieve subscriptions with a list of itemFilterTypes
	// either by injecting this in Subscription DAO and fetch itemfilterTypes there OR
	// use this to search for subscriptions that have a list of itemFilterTypes.


	Result saveRelation(int subscriptionPk, List<ItemFilterType> itemFilterTypes);

	Result saveSubscription(Subscription subscription);

	Result updateRelation(int subscriptionPk, List<ItemFilterType> itemFilterTypes);

	Result updateSubscription(Subscription subscription);

	Result deleteRelation(int subscriptionPk);

	Result deleteSubscription(int subscriptionPk);

	Subscription getSubscription(int subscriptionPk);

	List<Subscription> getAllSubscription();

}
