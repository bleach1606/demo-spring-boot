package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.reponse.response.CategoryResponse;
import com.example.demo.model.request.request.CategoryRequest;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

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
//
//    @DeleteMapping("{id}")
//    public BaseResponse<String> deleteById(@PathVariable Long id) {
//        return BaseResponse.ofSuccess("ok");
//    }


//    @GetMapping("{id}")
//    public BaseResponse<Category> getById(@PathVariable Long id) {
//        return BaseResponse.ofSuccess();
//    }
}
