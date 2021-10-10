package com.example.demo.mapper;

import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.model.reponse.response.ItemResponse;
import com.example.demo.model.reponse.response.SubCategoryDetailResponse;
import com.example.demo.model.reponse.response.SubCategoryResponse;
import com.example.demo.model.request.request.SubCategoryRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubCategoryMapper implements Mapper<SubCategory>{

    private final  ItemMapper itemMapper;

    public SubCategoryMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public SubCategory to(SubCategoryRequest subCategoryRequest){
        SubCategory subCategory = new SubCategory();
        Category category = new Category();
        category.setId(subCategoryRequest.getCategory_id());
        BeanUtils.copyProperties(subCategoryRequest,subCategory);
        subCategory.setCategory(category);
        return subCategory;
    }
    public SubCategoryResponse to(SubCategory subCategory){
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        BeanUtils.copyProperties(subCategory,subCategoryResponse);
        subCategoryResponse.setCategory_id(subCategory.getCategory().getId());
        return subCategoryResponse;
    }
    public SubCategoryDetailResponse toDetail(SubCategory subCategory){
        SubCategoryDetailResponse subCategoryDetailResponse = new SubCategoryDetailResponse();
        BeanUtils.copyProperties(subCategory,subCategoryDetailResponse);
        subCategoryDetailResponse.setCategory_id(subCategory.getId());
        if(subCategory.getItemList() != null){
            List<ItemResponse> itemList = itemMapper.toList(subCategory.getItemList(),itemMapper::to);
            subCategoryDetailResponse.setItemList(itemList);
        }
        return subCategoryDetailResponse;
    }
}
