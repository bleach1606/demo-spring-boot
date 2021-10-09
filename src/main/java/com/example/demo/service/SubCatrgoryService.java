package com.example.demo.service;

import com.example.demo.model.reponse.response.SubCategoryResponse;
import com.example.demo.model.request.request.SubCategoryRequest;

import java.util.List;


public interface SubCatrgoryService {

    SubCategoryResponse save(SubCategoryRequest subCategoryRequest);
    SubCategoryResponse findById(Long id);
    String deleteById(Long id);
    List<SubCategoryResponse> getAllSubCategory();
}
