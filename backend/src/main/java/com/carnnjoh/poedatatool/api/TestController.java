package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.model.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class TestController {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ValuableItemDAO itemDAO;

	private String itemJson;


	@GetMapping("/user")
	public String get() throws JsonProcessingException {

		Scanner scanner = new Scanner(getClass().getResourceAsStream("/item.txt"));
		StringBuilder sb = new StringBuilder();

		while(scanner.hasNext()) {
			sb.append(scanner.next());
		}

		itemJson = sb.toString();

		ValuableItem item = new ValuableItem();
		item.setId("qiowkepqormmprok");
		item.setSubscriptionFk(1);

		Item itemDeserialize = objectMapper.readValue(itemJson, Item.class);

		item.setItem(itemDeserialize);

		Result saveResult =  itemDAO.save(item);

		if(saveResult instanceof CreateSuccessResult){
			return "Yayy!!!" + ((CreateSuccessResult) saveResult).getPk();
		}
		return ":(";
	}

	@GetMapping("/testSelect")
	public String testSelect() throws JsonProcessingException {
		return ":(";
	}


	@GetMapping("/testDelete")
	public String testDelete() {
		return ":(";
	}

	@GetMapping("/testFetchAll")
	public String testFetchAll() {

		List<ValuableItem> item = itemDAO.fetchAll();

		if(item != null && item.size() > 0){
			return "Yayy!!!   " + item.stream().map(ValuableItem::toString).collect(Collectors.joining());
		}
		return ":(";
	}

	public <T extends Result, R> R mapResult(Result result, Function<T, R> action) {
		Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		if (type.isInstance(result)) {
			return action.apply((T) result);
		} else {
			return null;
		}
	}

}
