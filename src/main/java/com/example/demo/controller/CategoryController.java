package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.reponse.response.CategoryDetailResponse;
import com.example.demo.model.reponse.response.CategoryResponse;
import com.example.demo.model.request.request.CategoryRequest;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public-api/v1.0.0/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public BaseResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        return BaseResponse.ofSuccess(categoryService.save(request));
    }

    @DeleteMapping("{id}")
    public BaseResponse<String> deleteById(@PathVariable Long id) {
        return BaseResponse.ofSuccess("ok");
    }

    @GetMapping("{id}")
    public BaseResponse<CategoryDetailResponse> getById(@PathVariable Long id) {
        return BaseResponse.ofSuccess(categoryService.findById(id));
    }

    @GetMapping("")
    public BaseResponse<List<CategoryResponse>> getAllCategory() {
        return BaseResponse.ofSuccess(categoryService.getAll());
    }

    @PutMapping("{id}")
    public BaseResponse<CategoryResponse> updateById(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        return BaseResponse.ofSuccess(categoryService.updateById(id, categoryRequest));
    }

}
