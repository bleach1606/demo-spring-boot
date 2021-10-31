package com.example.demo.mapper;

import com.example.demo.entity.SubCategory;
import com.example.demo.model.request.Category.SubCategoryRequest;
import com.example.demo.utils.CusBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryMapper implements Mapper<SubCategory> {

    public SubCategory to(SubCategoryRequest request) {
        SubCategory subCategory = new SubCategory();
        CusBeanUtils.refine(request, subCategory, CusBeanUtils::copyNonNull);
        return subCategory;
    }

}
