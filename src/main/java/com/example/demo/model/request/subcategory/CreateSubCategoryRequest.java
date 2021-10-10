package com.example.demo.model.request.subcategory;

import lombok.Data;

@Data
public class CreateSubCategoryRequest {

    private String name;

    private String code;

    private Long categoryId;
}
