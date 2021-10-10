package com.example.demo.service;

import com.example.demo.model.reponse.response.ItemResponse;
import com.example.demo.model.request.request.ItemRequest;

import java.util.List;
public interface ItemService {

    ItemResponse save(ItemRequest itemRequest);
    ItemResponse updateById(Long id, ItemRequest itemRequest);
    ItemResponse findById(Long id);
    List<ItemResponse> getItemByPriceBetween(float minPrice, float maxPrice);
    List<ItemResponse> getItemByDateCreated(String date);
    String deleteById(Long id);
    List<ItemResponse> getAllItem();
}
