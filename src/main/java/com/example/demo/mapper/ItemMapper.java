package com.example.demo.mapper;

import com.example.demo.entity.Item;
import com.example.demo.model.response.item.ItemResponse;
import com.example.demo.model.response.subcategory.SubCategoryResponse;
import com.example.demo.model.request.item.ItemRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements Mapper<Item>{

    public Item to(ItemRequest itemRequest) {
        Item item = new Item();
        BeanUtils.copyProperties(itemRequest, item);
        return item;
    }

    public ItemResponse to(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        BeanUtils.copyProperties(item, itemResponse);
        if(item.getSubCategory() != null) {
            BeanUtils.copyProperties(item.getSubCategory(), subCategoryResponse);
            itemResponse.setSubCategoryResponse(subCategoryResponse);
        }
        return itemResponse;
    }
}
