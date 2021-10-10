package com.example.demo.controller;


import com.example.demo.model.BaseResponse;
import com.example.demo.model.response.subcategory.SubCategoryResponse;
import com.example.demo.model.request.subcategory.CreateSubCategoryRequest;
import com.example.demo.service.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public-api/v1.0.0/sub-category")
public class SubCategoryController {


    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping()
    public BaseResponse<List<SubCategoryResponse>> getAllCategory() {
        return BaseResponse.ofSuccess(subCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public BaseResponse<SubCategoryResponse> getById(@PathVariable Long id) {
        return BaseResponse.ofSuccess(subCategoryService.findById(id));
    }

    @PostMapping()
    public BaseResponse<SubCategoryResponse> saveCategory(@RequestBody CreateSubCategoryRequest request) {
        return BaseResponse.ofSuccess(subCategoryService.save(request));
    }
}
