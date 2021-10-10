package com.example.demo.model.response.item;

import com.example.demo.model.response.subcategory.SubCategoryResponse;
import lombok.Data;

@Data
public class ItemResponse {

    private Long id;

    private String name;

    private float price;

    private String description;

    private SubCategoryResponse subCategoryResponse;
}
