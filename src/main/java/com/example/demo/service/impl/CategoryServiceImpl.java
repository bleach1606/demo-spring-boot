package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.response.category.CategoryResponse;
import com.example.demo.model.request.category.CategoryRequest;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponse save(CategoryRequest request) {
        Category category = categoryMapper.to(request);
        return categoryMapper.to(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse update(CategoryRequest request, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CATEGORY)
        );
        category.setCode(request.getCode());
        category.setName(request.getName());
        return categoryMapper.to(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toList(categories, categoryMapper::to);
    }

    @Override
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CATEGORY)
        );
        return categoryMapper.to(category);
    }
}
