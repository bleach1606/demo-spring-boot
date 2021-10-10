package com.example.demo.service;

import com.example.demo.model.response.item.ItemResponse;
import com.example.demo.model.request.item.ItemRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

    ItemResponse save(ItemRequest itemRequest);

    ItemResponse update(ItemRequest itemRequest, Long id);

    List<ItemResponse> findAll();

    ItemResponse findById(Long id);

    Page<ItemResponse> findBySubCategoryId(Long id, Pageable pageable);

    boolean delete(Long ids[]);
}
