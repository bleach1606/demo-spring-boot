package com.example.demo.service;

import com.example.demo.model.reponse.response.CategoryDetailResponse;
import com.example.demo.model.reponse.response.CategoryResponse;
import com.example.demo.model.request.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse save(CategoryRequest request);

    CategoryDetailResponse findById(Long id);

    CategoryResponse updateById(Long id, CategoryRequest categoryRequest);

    String deleteByID(Long id);

    List<CategoryResponse> getAll();
}
