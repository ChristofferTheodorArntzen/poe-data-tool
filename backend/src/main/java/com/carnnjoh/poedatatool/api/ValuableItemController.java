package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.api.requestobjects.ValuableItemRequest;
import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.SuccessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/valuableItem")
public class ValuableItemController {

	private final static Logger logger =  LoggerFactory.getLogger(ValuableItemController.class);

	@Autowired
	private ValuableItemDAO valuableItemDAO;

	private final RestTemplate template  = new RestTemplate();

	@GetMapping("/{pk}")
	public ResponseEntity<ValuableItem> get(@PathVariable Integer pk) {
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		ValuableItem valuableItem = valuableItemDAO.fetch(pk);

		if(valuableItem != null) {
			return new ResponseEntity<>(valuableItem, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping()
	public ResponseEntity<List<ValuableItem>> getAll() {
		List<ValuableItem> valuableItem = valuableItemDAO.getAllByDate(false);

		if(valuableItem.size() > 0)
			return new ResponseEntity<>(valuableItem, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		if(id.isEmpty())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Result deleteResult = null;

		try {
			deleteResult = valuableItemDAO.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(deleteResult instanceof SuccessResult){
			logger.info("Deleted item with id: " + id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			logger.info("Failed to delete item with id: " + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping()
	public ResponseEntity<ValuableItem> post(@RequestBody ValuableItem valuableItem) {

		if(valuableItem == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Result postResult = valuableItemDAO.save(valuableItem);

		if(postResult instanceof CreateSuccessResult) {
			valuableItem.setPk(((CreateSuccessResult) postResult).getPk());
			return new ResponseEntity<>(valuableItem, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/{pk}")
	public ResponseEntity<ValuableItem> put(
			@PathVariable Integer pk,
			@Valid @RequestBody ValuableItemRequest valuableItemRequest
	) {
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		ValuableItem valuableItem = valuableItemDAO.fetch(pk);

		if(valuableItem == null){
			valuableItem = new ValuableItem();
		}

		valuableItem.setItem(valuableItemRequest.getItem());
		valuableItem.setSubscriptionFk(valuableItemRequest.getSubscriptionFk());
		valuableItem.setId(valuableItemRequest.getId());
		valuableItem.setMean(valuableItemRequest.getMean());
		valuableItem.setMedian(valuableItemRequest.getMedian());
		valuableItem.setMax(valuableItemRequest.getMax());
		valuableItem.setMin(valuableItemRequest.getMin());
		valuableItem.setCreatedDate(valuableItemRequest.getCreatedDate());

		Result putResult = (valuableItem.getPk() == null)
			? valuableItemDAO.save(valuableItem)
			: valuableItemDAO.update(valuableItem);

		if(putResult instanceof CreateSuccessResult){
			valuableItem.setPk(((CreateSuccessResult) putResult).getPk());
			return new ResponseEntity<>(valuableItem, HttpStatus.CREATED);
		}

		return (putResult instanceof SuccessResult)
			? new ResponseEntity<>(valuableItem, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}



