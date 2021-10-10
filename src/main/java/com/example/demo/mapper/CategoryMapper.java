package com.example.demo.mapper;

import com.example.demo.entity.Category;
import com.example.demo.model.reponse.response.CategoryDetailResponse;
import com.example.demo.model.reponse.response.CategoryResponse;
import com.example.demo.model.reponse.response.SubCategoryResponse;
import com.example.demo.model.request.request.CategoryRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CategoryMapper implements Mapper<Category> {

    private final SubCategoryMapper subCategoryMapper;

    public CategoryMapper(SubCategoryMapper subCategoryMapper) {
        this.subCategoryMapper = subCategoryMapper;
    }

    public Category to(CategoryRequest request){
        Category category = new Category();
        BeanUtils.copyProperties(request,category);
        return category;
    }

    public CategoryResponse to(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(category, categoryResponse);
        return categoryResponse;
    }

    public CategoryDetailResponse toDetail(Category category){
        CategoryDetailResponse categoryDetailResponse = new CategoryDetailResponse();
        BeanUtils.copyProperties(category,categoryDetailResponse);
        if(category.getSubCategoryList() != null){
            List<SubCategoryResponse> subCategoryResponseList = subCategoryMapper.toList(category.getSubCategoryList(),subCategoryMapper::to);
            categoryDetailResponse.setSubCategoryList(subCategoryResponseList);
        }
        return categoryDetailResponse;
    }

}
