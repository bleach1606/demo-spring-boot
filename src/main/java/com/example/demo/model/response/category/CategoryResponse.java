package com.example.demo.model.response.category;

import com.example.demo.model.response.subcategory.SubCategoryResponse;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private Long id;

    private String name;

    private String code;

    private List<SubCategoryResponse> subCategoryList;

}
