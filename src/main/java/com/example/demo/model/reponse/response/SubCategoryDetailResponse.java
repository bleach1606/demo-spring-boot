package com.example.demo.model.reponse.response;

import lombok.Data;

import java.util.List;

@Data
public class SubCategoryDetailResponse {
    private Long id;
    private String code;
    private String name;
    private Long category_id;
    private List<ItemResponse> itemList;
}
