package com.example.demo.service.impl;

import com.example.demo.entity.SubCategory;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.SubCategoryMapper;
import com.example.demo.model.reponse.response.SubCategoryResponse;
import com.example.demo.model.request.request.SubCategoryRequest;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.SubCatrgoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SubCategoryServiceImpl implements SubCatrgoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryMapper subCategoryMapper;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, SubCategoryMapper subCategoryMapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.subCategoryMapper = subCategoryMapper;
    }

    @Override
    public SubCategoryResponse save(SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = subCategoryMapper.to(subCategoryRequest);
        return subCategoryMapper.to(subCategoryRepository.saveAndFlush(subCategory));
    }

    @Override
    public SubCategoryResponse findById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_SUBCATEGORY)
        );

        return subCategoryMapper.to(subCategory);
    }

    @Override
    public String deleteById(Long id) {
        subCategoryRepository.deleteById(id);
        return "OK";
    }

    @Override
    public List<SubCategoryResponse> getAllSubCategory() {
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        return subCategoryList.stream().map(subCategoryMapper::to).collect(Collectors.toList());
    }
}
