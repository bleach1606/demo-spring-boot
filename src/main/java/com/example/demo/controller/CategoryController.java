package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.reponse.Category.CategoryResponse;
import com.example.demo.service.CategoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("public-api/v1.0.0/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "15"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string",
                    paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. Multiple sort criteria are supported.")})
    public BaseResponse<Page<CategoryResponse>> searchByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @ApiIgnore Pageable pageable
    ) {
        return BaseResponse.ofSuccess(categoryService.findByFilter(name, code, pageable));
    }
}
