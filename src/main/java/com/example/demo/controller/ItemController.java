package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.reponse.response.ItemResponse;
import com.example.demo.model.request.request.ItemRequest;
import com.example.demo.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public-api/v1.0.0/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public BaseResponse<ItemResponse> createItem(@RequestBody ItemRequest itemRequest) {
        return BaseResponse.ofSuccess(itemService.save(itemRequest));
    }


    @GetMapping("{id}")
    public BaseResponse<ItemResponse> getById(@PathVariable Long id) {
        return BaseResponse.ofSuccess(itemService.findById(id));
    }

    @GetMapping("")
    public BaseResponse<List<ItemResponse>> getAllItem() {
        return BaseResponse.ofSuccess(itemService.getAllItem());
    }

    @DeleteMapping("{id}")
    public BaseResponse<String> deleteByID(@PathVariable Long id) {
        return BaseResponse.ofSuccess(itemService.deleteById(id));
    }
}
