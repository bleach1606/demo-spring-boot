package com.example.demo.mapper;

import com.example.demo.entity.Item;
import com.example.demo.model.reponse.response.ItemResponse;
import com.example.demo.model.request.request.ItemRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper implements Mapper<Item> {

    public Item toEntity(ItemRequest itemRequest) {
        Item item = new Item();
        BeanUtils.copyProperties(itemRequest, item);
        return item;
    }

    public ItemResponse to(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        BeanUtils.copyProperties(item, itemResponse);
        return itemResponse;
    }

//    public List<Item> to(List<ItemRequest> itemRequests){
//
//    }
}

