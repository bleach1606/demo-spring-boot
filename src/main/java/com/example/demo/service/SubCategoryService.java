package com.example.demo.service;

import com.example.demo.model.response.subcategory.SubCategoryResponse;
import com.example.demo.model.request.subcategory.CreateSubCategoryRequest;

import java.util.List;

public interface SubCategoryService {

    SubCategoryResponse save(CreateSubCategoryRequest request);

    List<SubCategoryResponse> findAll();

    SubCategoryResponse findById(Long id);
}
