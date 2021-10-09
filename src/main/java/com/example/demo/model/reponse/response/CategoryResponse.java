package com.example.demo.model.reponse.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private Long id;
    private String code;
    private String name;
    private List<SubCategoryResponse> subCategoryList;
}
