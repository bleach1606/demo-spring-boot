package com.example.demo.mapper;

import com.example.demo.entity.Category;
import com.example.demo.model.response.category.CategoryResponse;
import com.example.demo.model.response.subcategory.SubCategoryResponse;
import com.example.demo.model.request.category.CategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CategoryMapper implements Mapper<Category>{

    private final SubCategoryMapper subCategoryMapper;

    public Category to(CategoryRequest request) {
        Category category = new Category();
        BeanUtils.copyProperties(request, category);
        return category;
    }

    public CategoryResponse to(Category category) {
        CategoryResponse response = new CategoryResponse();
        BeanUtils.copyProperties(category, response);
        if(category.getSubCategoryList() != null) {
            List<SubCategoryResponse> subCategoryList =
                    subCategoryMapper.toList(category.getSubCategoryList(), subCategoryMapper::to);
            response.setSubCategoryList(subCategoryList);
        }
        return response;
    }

}
