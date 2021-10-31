package com.example.demo.mapper;

import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.model.reponse.Category.CategoryResponse;
import com.example.demo.model.request.Category.CategoryRequest;
import com.example.demo.model.request.Category.SubCategoryRequest;
import com.example.demo.utils.CusBeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CategoryMapper implements Mapper<Category> {

    private final SubCategoryMapper subCategoryMapper;

    public CategoryMapper(SubCategoryMapper subCategoryMapper) {
        this.subCategoryMapper = subCategoryMapper;
    }

    public Category to(CategoryRequest request) {
        Category category = new Category();
        CusBeanUtils.refine(request, category, CusBeanUtils::copyNonNull);
        if (CollectionUtils.isNotEmpty(request.getSubCategoryList())) {
            List<SubCategory> subCategoryList = request
                    .getSubCategoryList()
                    .stream()
                    .map(subCategoryMapper::to)
                    .collect(Collectors.toList());
            category.setSubCategoryList(subCategoryList);
        }
        return category;
    }

    public CategoryResponse to(Category category) {
        CategoryResponse response = new CategoryResponse();
        CusBeanUtils.refine(category, response, CusBeanUtils::copyNonNull);
        return response;
    }

}
