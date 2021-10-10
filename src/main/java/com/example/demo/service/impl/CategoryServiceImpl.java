package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.reponse.response.CategoryDetailResponse;
import com.example.demo.model.reponse.response.CategoryResponse;
import com.example.demo.model.request.request.CategoryRequest;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
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
        return categoryMapper.to(categoryRepository.saveAndFlush(category));
    }

    @Override
    public CategoryDetailResponse findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CATEGORY)
        );
        return categoryMapper.toDetail(category);
    }

    @Override
    public CategoryResponse updateById(Long id, CategoryRequest categoryRequest) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setCode(categoryRequest.getCode());
                    category.setName(categoryRequest.getName());
                    return categoryMapper.to(categoryRepository.saveAndFlush(category));
                })
                .orElseThrow(() -> new BusinessException(BusinessCode.NOT_FOUND_CATEGORY));
    }

    @Override
    public String deleteByID(Long id) {
        categoryRepository.deleteById(id);
        return "Ok";
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(categoryMapper::to).collect(Collectors.toList());
    }

}
