package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
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
@RequestMapping("/valuableItem")
public class ValuableItemController {

	@Autowired
	private ValuableItemDAO valuableItemDAO;

	private final RestTemplate template  = new RestTemplate();

	@GetMapping("/{pk}")
	public ResponseEntity<ValuableItem> get(@PathVariable Integer pk) {
		if(pk < 0)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		ValuableItem valuableItem = valuableItemDAO.fetch(pk);

		if(valuableItem != null) {
			return new ResponseEntity<>(valuableItem, HttpStatus.OK);
		}

		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@GetMapping()
	public ResponseEntity<List<ValuableItem>> getAll() {
		List<ValuableItem> valuableItem = valuableItemDAO.fetchAll();

		if(valuableItem.size() > 0)
			return new ResponseEntity<>(valuableItem, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{pk}")
	public ResponseEntity<ValuableItem> delete(@PathVariable int pk) {
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Result deleteResult = valuableItemDAO.deleteByPk(pk);

		if(deleteResult instanceof SuccessResult)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
	public ResponseEntity<ValuableItem> put(@PathVariable Integer pk, @RequestBody ValuableItem valuableItemDetails) {
		if(pk < 0)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		ValuableItem valuableItem = valuableItemDAO.fetch(pk);

		if(valuableItem == null){
			valuableItem = new ValuableItem();
		}

		valuableItem.setItem(valuableItemDetails.getItem());
		valuableItem.setSubscriptionFk(valuableItemDetails.getSubscriptionFk());
		valuableItem.setId(valuableItemDetails.getId());

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

//	private ValuableItem test() throws JsonProcessingException {
//
//		Scanner scanner = new Scanner(getClass().getResourceAsStream("/item.txt"));
//		StringBuilder sb = new StringBuilder();
//
//		while(scanner.hasNext()) {
//			sb.append(scanner.next());
//		}
//
//		itemJson = sb.toString();
//
//		ValuableItem item = new ValuableItem();
//		item.setId("qiowkepqormmprok");
//		item.setSubscriptionFk(1);
//
//		Item itemDeserialize;
//		try {
//			itemDeserialize = objectMapper.readValue(itemJson, Item.class);
//			item.setItem(itemDeserialize);
//
//			return item;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

}