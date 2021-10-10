package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.reponse.response.ItemResponse;
import com.example.demo.model.request.request.ItemRequest;
import com.example.demo.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("get-item-price-range/{min-price}/{max-price}")
    public BaseResponse<List<ItemResponse>> getItemPriceRange(@PathVariable("min-price") Float minPrice, @PathVariable("max-price") Float maxPrice) {
        return BaseResponse.ofSuccess(itemService.getItemByPriceBetween(minPrice,maxPrice));
    }

    @GetMapping("get-item-by-date-created/{date}")
    public BaseResponse<List<ItemResponse>> getItemByDateCreated(@PathVariable("date") String date) {
        return BaseResponse.ofSuccess(itemService.getItemByDateCreated(date));
    }

    @GetMapping("")
    public BaseResponse<List<ItemResponse>> getAllItem(){
        return BaseResponse.ofSuccess(itemService.getAllItem());
    }
    @DeleteMapping("{id}")
    public BaseResponse<String> deleteByID(@PathVariable Long id) {
        return BaseResponse.ofSuccess(itemService.deleteById(id));
    }

    @PutMapping("{id}")
    public  BaseResponse<ItemResponse> updateById(@PathVariable Long id, @RequestBody ItemRequest itemRequest){
        return BaseResponse.ofSuccess((itemService.updateById(id,itemRequest)));
    }
}
