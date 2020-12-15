package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "/subscription")
public class SubscriptionController {

	@Autowired
	private SubscriptionDAO subscriptionDAO;

	private final RestTemplate template = new RestTemplate();

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
	public ResponseEntity<Subscription> post(@RequestBody Subscription subscription) {

		if(subscription == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Result postResult = subscriptionDAO.save(subscription);

		if(postResult instanceof CreateSuccessResult) {
			subscription.setPk(((CreateSuccessResult) postResult).getPk());
			return new ResponseEntity<>(subscription, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/{pk}")
	public ResponseEntity<Subscription> put(@PathVariable Integer pk, @RequestBody Subscription subscriptionDetails) {
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Subscription subscription = subscriptionDAO.fetch(pk);

		if(subscription == null) {
			subscription = new Subscription();
		}

		subscription.setTabIds(subscriptionDetails.getTabIds());
		subscription.setThreshold(subscriptionDetails.getThreshold());
		subscription.setThresholdCurrencyType(subscriptionDetails.getThresholdCurrencyType());

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