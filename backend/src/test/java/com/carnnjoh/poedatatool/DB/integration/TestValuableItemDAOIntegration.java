package com.carnnjoh.poedatatool.DB.integration;

import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.DeleteSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.UpdateSuccessResult;
import com.carnnjoh.poedatatool.model.Item;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class TestValuableItemDAOIntegration {

	@Autowired
	ValuableItemDAO valuableItemDAO;

	ValuableItem testValuableItem;

	@BeforeEach
	public void setup() {

		LocalDateTime time = LocalDateTime.of(2020, 1, 1, 1, 1, 1);

		testValuableItem = new ValuableItem("123123", 1, new Item(), 20, 22, 25, 18, time);

		Result saveResult = valuableItemDAO.save(testValuableItem);
		testValuableItem.setPk(((CreateSuccessResult)saveResult).getPk());
	}

	@AfterEach
    public void flush() {
	    List<ValuableItem> valuableItems = valuableItemDAO.fetchAll();

	    for(ValuableItem valuableItem : valuableItems) {
	        valuableItemDAO.deleteByPk(valuableItem.getPk());
        }
    }

    @Test
    public void testSave() {
	    Result saveResult = valuableItemDAO.save(testValuableItem);

	    assertThat(saveResult).isInstanceOf(CreateSuccessResult.class);

	    ValuableItem fetchedValuableItem = valuableItemDAO.fetch(testValuableItem.getPk());

        assertThat(fetchedValuableItem.getPk()).isEqualTo(testValuableItem.getPk());
        assertThat(fetchedValuableItem.getMean()).isEqualTo(testValuableItem.getMean());
        assertThat(fetchedValuableItem.getMax()).isEqualTo(testValuableItem.getMax());
        assertThat(fetchedValuableItem.getMin()).isEqualTo(testValuableItem.getMin());
        assertThat(fetchedValuableItem.getMedian()).isEqualTo(testValuableItem.getMedian());
        assertThat(fetchedValuableItem.getId()).isEqualTo(testValuableItem.getId());
        assertThat(fetchedValuableItem.getCreatedDate()).isEqualTo(testValuableItem.getCreatedDate());
        assertThat(fetchedValuableItem.getItem().itemId).isEqualTo(testValuableItem.getItem().itemId);
    }

    @Test
    public void testUpdate() {
        ValuableItem fetchedValuableItem = valuableItemDAO.fetch(testValuableItem.getPk());

        fetchedValuableItem.setMax(1000);
        fetchedValuableItem.setMean(1);

        Result updateResult = valuableItemDAO.update(fetchedValuableItem);
        assertThat(updateResult).isInstanceOf(UpdateSuccessResult.class);

        ValuableItem updatedValuableItem = valuableItemDAO.fetch(testValuableItem.getPk());

        assertThat(updatedValuableItem.getMax()).isNotEqualTo(testValuableItem.getMax());
        assertThat(updatedValuableItem.getMean()).isNotEqualTo(testValuableItem.getMean());

        assertThat(updatedValuableItem.getPk()).isEqualTo(testValuableItem.getPk());
        assertThat(updatedValuableItem.getMin()).isEqualTo(testValuableItem.getMin());
        assertThat(updatedValuableItem.getMedian()).isEqualTo(testValuableItem.getMedian());
        assertThat(updatedValuableItem.getId()).isEqualTo(testValuableItem.getId());
        assertThat(updatedValuableItem.getCreatedDate()).isEqualTo(testValuableItem.getCreatedDate());
        assertThat(updatedValuableItem.getItem().itemId).isEqualTo(testValuableItem.getItem().itemId);
    }

    @Test
    public void testDelete() {
	    ValuableItem fetchedValuableItem = valuableItemDAO.fetch(testValuableItem.getPk());

	    Result deleteResult = valuableItemDAO.deleteByPk(fetchedValuableItem.getPk());
	    assertThat(deleteResult).isInstanceOf(DeleteSuccessResult.class);

	    fetchedValuableItem = valuableItemDAO.fetch(fetchedValuableItem.getPk());
	    assertThat(fetchedValuableItem).isNull();
    }

    @Test
    public void testFetch() {
	    ValuableItem valuableItem = valuableItemDAO.fetch(testValuableItem.getPk());
	    assertThat(valuableItem).isNotNull();
	    assertThat(valuableItem.getPk()).isEqualTo(testValuableItem.getPk());
    }

	@Test
	public void testFetchAll() {
		generateValuableItems();
		List<ValuableItem> valuableItems = valuableItemDAO.fetchAll();
		assertThat(valuableItems.size() > 0).isTrue();

		for(ValuableItem valuableItem : valuableItems) {
		    assertThat(valuableItem.getPk()).isNotNull();
        }

	}

	private void generateValuableItems() {
		generateValuableItems(100);
	}

	private void generateValuableItems(int numberOfValuableItems) {

		List<ValuableItem> valuableItems = new ArrayList<>();

		for (int i = 0; i < numberOfValuableItems; i++) {
			valuableItems.add(new ValuableItem("qwe" +  i + "qwe", 1, new Item(), i, i+i, i-1, 0, LocalDateTime.now()));
		}

		for(ValuableItem valuableItem : valuableItems) {
			valuableItemDAO.save(valuableItem);
		}
	}

}
