package com.example.demo.mapper;

import com.example.demo.entity.Item;
import com.example.demo.entity.SubCategory;
import com.example.demo.model.reponse.response.ItemResponse;
import com.example.demo.model.request.request.ItemRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
public class ItemMapper implements Mapper<Item> {

    public Item to(ItemRequest itemRequest) {
        Item item = new Item();
        SubCategory subCategory = new SubCategory();
        subCategory.setId(itemRequest.getSub_category_id());
        BeanUtils.copyProperties(itemRequest, item);
        item.setSubCategory(subCategory);
        return item;
    }

    public ItemResponse to(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        BeanUtils.copyProperties(item, itemResponse);
        itemResponse.setSub_category_id(item.getSubCategory().getId());
        return itemResponse;
    }

}

