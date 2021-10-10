package com.example.demo.model.request.request;

import lombok.Data;

@Data
public class ItemRequest {
    private String name;
    private float price;
    private Long sub_category_id;
}
