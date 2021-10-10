package com.example.demo.model.request.request;

import lombok.Data;

import java.util.List;

@Data
public class SubCategoryRequest {
    private String code;
    private String name;
    private Long category_id;
}
