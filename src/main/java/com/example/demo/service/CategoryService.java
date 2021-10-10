package com.example.demo.service;

import com.example.demo.model.response.category.CategoryResponse;
import com.example.demo.model.request.category.CategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse save(CategoryRequest request);

    CategoryResponse update(CategoryRequest request, Long id);

    List<CategoryResponse> findAll();

    CategoryResponse findById(Long id);



}
