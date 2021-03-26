package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.api.requestobjects.SubscriptionRequest;
import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.SuccessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/subscription")
public class SubscriptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private SubscriptionDAO subscriptionDAO;

	@GetMapping("/{pk}")
	public ResponseEntity<Subscription> get(@PathVariable Integer pk) {
		if(pk < 0)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		Subscription subscription = subscriptionDAO.fetch(pk);

		if(subscription != null) {
			return new ResponseEntity<>(subscription, HttpStatus.OK);
		}

		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@GetMapping()
	public ResponseEntity<List<Subscription>> getAll() {
		List<Subscription> subscriptions = subscriptionDAO.fetchAll();

		if(subscriptions.size() > 0)
			return new ResponseEntity<>(subscriptions, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{pk}")
	public ResponseEntity<Subscription> delete(@PathVariable int pk) {
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Result deleteResult = subscriptionDAO.deleteByPk(pk);

		if(deleteResult instanceof SuccessResult)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public ResponseEntity<Subscription> post(@RequestBody SubscriptionRequest subscriptionRequest) {

		if(subscriptionRequest == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Subscription subscription = new Subscription(subscriptionRequest);

		Result postResult = subscriptionDAO.save(subscription);

		if(postResult instanceof SuccessResult) {
			subscription.setPk(((CreateSuccessResult) postResult).getPk());
			return new ResponseEntity<>(subscription, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/{pk}")
	public ResponseEntity<Subscription> put(
			@PathVariable Integer pk,
			@Valid @RequestBody SubscriptionRequest subscriptionRequest
	) {
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Subscription subscription = subscriptionDAO.fetch(pk);

		if(subscription == null) {
			subscription = new Subscription();
		}

		subscription.setTabIds(subscriptionRequest.getTabIds());
		subscription.setCurrencyThreshold(subscriptionRequest.getCurrencyThreshold());
		subscription.setCurrencyType(subscriptionRequest.getCurrencyType());
		subscription.setName(subscriptionRequest.getName());
		subscription.setItemTypes(subscriptionRequest.getItemTypes());
		subscription.setActive(subscriptionRequest.isActive());

		Result putResult = (subscription.getPk() == null)
			? subscriptionDAO.save(subscription)
			: subscriptionDAO.update(subscription);

		if(putResult instanceof CreateSuccessResult) {
			subscription.setPk(((CreateSuccessResult) putResult).getPk());
			return new ResponseEntity<>(subscription, HttpStatus.CREATED);
		}

		return (putResult instanceof SuccessResult)
			? new ResponseEntity<>(subscription, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
