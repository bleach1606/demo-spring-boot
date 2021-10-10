package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.SubCategoryMapper;
import com.example.demo.model.response.subcategory.SubCategoryResponse;
import com.example.demo.model.request.subcategory.CreateSubCategoryRequest;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository repository;

    private final SubCategoryMapper mapper;

    private final CategoryRepository categoryRepository;


    @Override
    public SubCategoryResponse save(CreateSubCategoryRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CATEGORY)
        );
        SubCategory subCategory = mapper.to(request);
        subCategory.setCategory(category);
        return mapper.to(repository.save(subCategory));
    }

    @Override
    public List<SubCategoryResponse> findAll() {
        List<SubCategory> subCategoryList = repository.findAll();
        return mapper.toList(subCategoryList, mapper::to);
    }

    @Override
    public SubCategoryResponse findById(Long id) {
        SubCategory subCategory = repository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_SUB_CATEGORY)
        );
        return mapper.to(subCategory);
    }
}
