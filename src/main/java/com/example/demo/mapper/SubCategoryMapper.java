package com.example.demo.mapper;

import com.example.demo.entity.SubCategory;
import com.example.demo.model.response.subcategory.SubCategoryResponse;
import com.example.demo.model.request.subcategory.CreateSubCategoryRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryMapper implements Mapper<SubCategory>{

    public SubCategory to(CreateSubCategoryRequest request) {
        SubCategory subCategory = new SubCategory();
        BeanUtils.copyProperties(request, subCategory);
        return subCategory;
    }

    public SubCategoryResponse to(SubCategory subCategory) {
        SubCategoryResponse response = new SubCategoryResponse();
        BeanUtils.copyProperties(subCategory, response);
        return response;
    }

}
