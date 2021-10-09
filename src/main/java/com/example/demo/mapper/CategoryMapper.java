package com.example.demo.mapper;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.entity.SubCategory;
import com.example.demo.model.reponse.response.CategoryResponse;
import com.example.demo.model.reponse.response.SubCategoryResponse;
import com.example.demo.model.request.request.CategoryRequest;
import com.example.demo.model.request.request.ItemRequest;
import com.example.demo.model.request.request.SubCategoryRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper implements Mapper<Category> {

    public Category to(CategoryRequest request){
        Category category = new Category();
        List<SubCategory> subCategoryList = new ArrayList<>();
        BeanUtils.copyProperties(request,category);
        for (SubCategoryRequest subCategoryRequest : request.getSubCategoryList()){
            SubCategory subCategory = new SubCategory();
            List<Item> itemList = new ArrayList<>();
            BeanUtils.copyProperties(subCategoryRequest,subCategory);
            for(ItemRequest itemRequest : subCategoryRequest.getItemList()){
                Item item = new Item();
                BeanUtils.copyProperties(itemRequest,item);
                itemList.add(item);
            }
            subCategory.setItemList(itemList);
            subCategoryList.add(subCategory);
        }
        category.setSubCategoryList(subCategoryList);
        return category;
    }

    public CategoryResponse to(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        List<SubCategoryResponse> subCategoryList = new ArrayList<>();
        BeanUtils.copyProperties(category, categoryResponse);
        for(SubCategory subCategory: category.getSubCategoryList()){
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
            BeanUtils.copyProperties(subCategory, subCategoryResponse);
            subCategoryList.add(subCategoryResponse);
        }
        categoryResponse.setSubCategoryList(subCategoryList);
        return categoryResponse;
    }

}
