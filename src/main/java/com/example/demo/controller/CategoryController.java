package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.reponse.response.UserResponse;
import com.example.demo.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public-api/v1.0.0/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @DeleteMapping("{id}")
    public BaseResponse<String> deleteById(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return BaseResponse.ofSuccess("ok");
    }

    @GetMapping("{id}")
    public BaseResponse<Category> getById(@PathVariable Long id) {
        return BaseResponse.ofSuccess(categoryRepository.findById(id).orElse(null));
    }
}
