package com.example.demo.model.request.Category;

import lombok.Data;

import java.util.List;


@Data
public class CategoryRequest {

    private String code;
    private String name;
    private List<SubCategoryRequest> subCategoryList;
}
