package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.reponse.response.CategoryResponse;
import com.example.demo.model.request.request.CategoryRequest;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
        return  categoryMapper.to(categoryRepository.saveAndFlush(category));
    }

    @Override
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CATEGORY)
        );
        return categoryMapper.to(category);
    }

    @Override
    public String deleteByID(Long id) {
        categoryRepository.deleteById(id);
        return "Ok";
    }

//    @Override
//    public CategoryResponse findById(Long id) {
//        Category category = categoryRepository.findById(id).orElseThrow(
//                () -> new BusinessException(BusinessCode.)
//        )
//    }
}
