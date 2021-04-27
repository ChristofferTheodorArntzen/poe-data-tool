package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.model.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping( path = "/itemTypes")
public class ItemTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemTypeController.class);

    private List<ItemType> itemTypes = new ArrayList<>(EnumSet.allOf(ItemType.class));

    @GetMapping()
    public ResponseEntity<List<ItemType>> getAll( ){
        return new ResponseEntity<>(itemTypes, HttpStatus.OK);
    }

}
