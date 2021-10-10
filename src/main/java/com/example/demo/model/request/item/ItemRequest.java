package com.example.demo.model.request.item;

import lombok.Data;

@Data
public class ItemRequest {

    private String name;

    private float price;

    private String description;

    private Long subCategoryId;
}
