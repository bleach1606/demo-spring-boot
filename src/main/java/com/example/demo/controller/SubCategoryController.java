package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.reponse.response.SubCategoryResponse;
import com.example.demo.model.request.request.SubCategoryRequest;
import com.example.demo.service.SubCatrgoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public-api/v1.0.0/sub-category")
public class SubCategoryController {
    private final SubCatrgoryService subCatrgoryService;

    public SubCategoryController(SubCatrgoryService subCatrgoryService) {
        this.subCatrgoryService = subCatrgoryService;
    }

    @PostMapping
    public BaseResponse<SubCategoryResponse> createSubCategory(@RequestBody SubCategoryRequest subCategoryRequest) {
        return BaseResponse.ofSuccess(subCatrgoryService.save(subCategoryRequest));
    }

    @GetMapping("{id}")
    public BaseResponse<SubCategoryResponse> getById(@PathVariable Long id) {
        return BaseResponse.ofSuccess(subCatrgoryService.findById(id));
    }

    @DeleteMapping("{id}")
    public BaseResponse<String> deleteById(@PathVariable Long id) {
        return BaseResponse.ofSuccess(subCatrgoryService.deleteById(id));
    }

    @GetMapping("")
    public BaseResponse<List<SubCategoryResponse>> getAllSubCategory() {
        return BaseResponse.ofSuccess(subCatrgoryService.getAllSubCategory());
    }
}
