package com.example.demo.mapper;

import com.example.demo.entity.Item;
import com.example.demo.entity.SubCategory;
import com.example.demo.model.reponse.response.ItemResponse;
import com.example.demo.model.reponse.response.SubCategoryResponse;
import com.example.demo.model.request.request.ItemRequest;
import com.example.demo.model.request.request.SubCategoryRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubCategoryMapper implements Mapper<SubCategory>{

    private final  ItemMapper itemMapper;

    public SubCategoryMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public SubCategory to(SubCategoryRequest subCategoryRequest){
        SubCategory subCategory = new SubCategory();
        List<Item> itemList = new ArrayList<>();
        BeanUtils.copyProperties(subCategoryRequest,subCategory);
        itemList = itemMapper.toList(subCategoryRequest.getItemList(), itemMapper::toEntity);
        subCategory.setItemList(itemList);
        return subCategory;
    }
    public SubCategoryResponse to(SubCategory subCategory){
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        List<ItemResponse> itemResponseList = new ArrayList<>();
        BeanUtils.copyProperties(subCategory,subCategoryResponse);
        for(Item item : subCategory.getItemList()){
            ItemResponse itemResponse = new ItemResponse();
            BeanUtils.copyProperties(item,itemResponse);
            itemResponseList.add(itemResponse);
        }
        subCategoryResponse.setItemList(itemResponseList);
        return subCategoryResponse;
    }
}
