package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.TestUtils;
import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.InterpretedMod;
import com.carnnjoh.poedatatool.model.PrivateStashTab;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest.QueryRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

/*
    TODO: this test class is not a "normal" test class. It follows the same flow of PrivateStashTabPoller to simulate and test the creation of inMemoryItems
     Separate this into smaller test classes, each testing its own service, methods etc.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class InMemoryItemFlowTest {

    @Autowired
    QueryConstructorService service;

    @Autowired
    PrivateStashTabService privateStashTabService;

    @Autowired
    ObjectMapper mapper;

    PrivateStashTab privateStashTab;

    @BeforeAll
    public void setUp() {
        privateStashTab = TestUtils.getFileFromResourcesAsObject("/privateStashTab.json", PrivateStashTab.class);
    }

    @Test
    public void testConstructQueryRequest() {

        privateStashTabService.saveItems(Collections.singletonList(privateStashTab));

        Map<String, InMemoryItem> inMemoryItemMap = privateStashTabService.getInMemoryItemMap();

        for(InMemoryItem inMemoryItem : inMemoryItemMap.values()) {

            if(inMemoryItem.getInterpretedMods() != null && inMemoryItem.getInterpretedMods().size() > 0) {
                List<InterpretedMod> interpretedMod = inMemoryItem.getInterpretedMods();
                interpretedMod.forEach(i -> System.out.println(i.toString()));
            }

        }

        inMemoryItemMap.forEach( (modId, inMemoryItem ) -> inMemoryItem.setSearch(true));

        List<QueryRequest> queryRequests = service.createQueryRequests(inMemoryItemMap);

        System.out.println("Size of query request list : " + queryRequests.size());

        try {

            for (QueryRequest query : queryRequests) {

//                File file = new File("C:/dataDump/" + query.hashCode() + ".json");
//                mapper.writerWithDefaultPrettyPrinter().writeValue(file, query);

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }


}
