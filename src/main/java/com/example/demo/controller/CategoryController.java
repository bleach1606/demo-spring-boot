package com.example.demo.controller;

import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorResponse;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.response.category.CategoryResponse;
import com.example.demo.model.request.category.CategoryRequest;
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

    @GetMapping()
    public BaseResponse<List<CategoryResponse>> getAllCategory() {
        return BaseResponse.ofSuccess(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public BaseResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return BaseResponse.ofSuccess(categoryService.findById(id));
    }

    @PostMapping()
    public BaseResponse<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        return BaseResponse.ofSuccess(categoryService.save(request));
    }

    @PutMapping("/{id}")
    public BaseResponse<CategoryResponse> updateCategory(@RequestBody CategoryRequest request,@PathVariable Long id) {
        return BaseResponse.ofSuccess(categoryService.update(request, id));
    }
}
