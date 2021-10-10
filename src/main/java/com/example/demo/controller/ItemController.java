package com.example.demo.controller;

import com.example.demo.model.BaseResponse;
import com.example.demo.model.response.item.ItemResponse;
import com.example.demo.model.request.item.ItemRequest;
import com.example.demo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("public-api/v1.0.0/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping()
    public BaseResponse<ItemResponse> save(@RequestBody ItemRequest itemRequest) {
        return BaseResponse.ofSuccess(itemService.save(itemRequest));
    }

    @GetMapping()
    public BaseResponse<List<ItemResponse>> getAllItem(){
        return BaseResponse.ofSuccess(itemService.findAll());

    }

    @GetMapping("/{id}")
    public BaseResponse<ItemResponse> getItemById(@PathVariable Long id) {
        return BaseResponse.ofSuccess(itemService.findById(id));
    }

    @PutMapping("/{id}")
    public BaseResponse<ItemResponse> updateItem(@RequestBody ItemRequest itemRequest, @PathVariable Long id) {
        return BaseResponse.ofSuccess(itemService.update(itemRequest, id));
    }

    @DeleteMapping()
    public BaseResponse<Boolean> deleteItems(@RequestBody Long[] ids) {
        return BaseResponse.ofSuccess(itemService.delete(ids));
    }

    @GetMapping("/sub-category/{categoryId}")
    public BaseResponse<?> getItemsByCategoryId(@PathVariable Long categoryId,@ApiIgnore Pageable pageable) {
        return BaseResponse.ofSuccess(itemService.findBySubCategoryId(categoryId, pageable));
    }

}
