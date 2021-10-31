package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.reponse.Category.CategoryResponse;
import com.example.demo.model.request.Category.CategoryRequest;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;

    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(rollbackFor = {BusinessException.class, Exception.class})
    public CategoryResponse save(CategoryRequest request) {
        Category category = categoryMapper.to(request);
        category = categoryRepo.save(category);
        return categoryMapper.to(category);
    }

    @Override
    public CategoryResponse findById(Long id) {
        // Lambda
        Category category = categoryRepo.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.INTERNAL_SERVER)
        );
        return categoryMapper.to(category);
    }

    @Override
    public List<CategoryResponse> findAll() {
        // Method reference
        List<Category> categories = categoryRepo.findAll();
        return categoryMapper.toList(categories, categoryMapper::to);
    }

    @Override
    public Page<CategoryResponse> findByFilter(String name, String code, Pageable pageable) {
        // cach 1 : sql (native query)
        if (StringUtils.isBlank(name)) {
            name = StringUtils.EMPTY;
        }

        if (StringUtils.isBlank(code)) {
            code = StringUtils.EMPTY;
        }

        // validate
        name = "%" + name.toLowerCase() + "%";
        code = "%" + code.toLowerCase() + "%";
        return categoryRepo.findAllByFilter2(name, code, pageable).map(categoryMapper::to);
    }
}
