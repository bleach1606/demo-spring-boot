package com.example.demo.service;

import com.example.demo.model.reponse.response.CategoryResponse;
import com.example.demo.model.request.request.CategoryRequest;

public interface CategoryService {

    CategoryResponse save(CategoryRequest request);

    CategoryResponse findById(Long id);

    String deleteByID(Long id);
}
