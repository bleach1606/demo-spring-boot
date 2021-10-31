package com.example.demo.service;

import com.example.demo.model.reponse.Category.CategoryResponse;
import com.example.demo.model.request.Category.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    CategoryResponse save(CategoryRequest request);

    CategoryResponse findById(Long id);

    List<CategoryResponse> findAll();

    Page<CategoryResponse> findByFilter(String name, String code, Pageable pageable);
}
