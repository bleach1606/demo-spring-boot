package com.example.demo.service.impl;

import com.example.demo.entity.Item;
import com.example.demo.entity.SubCategory;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.model.response.item.ItemResponse;
import com.example.demo.model.request.item.ItemRequest;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final SubCategoryRepository subCategoryRepository;

    private ItemMapper itemMapper;

    @Override
    public ItemResponse save(ItemRequest itemRequest) {
        Item item = itemMapper.to(itemRequest);
        SubCategory subCategory = subCategoryRepository.findById(itemRequest.getSubCategoryId()).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_SUB_CATEGORY)
        );
        item.setSubCategory(subCategory);
        return itemMapper.to(itemRepository.save(item));
    }

    @Override
    public ItemResponse update(ItemRequest itemRequest, Long id) {
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_ITEM)
        );
        if(item != null) {
            item = itemMapper.to(itemRequest);
            item.setId(id);
            SubCategory subCategory = subCategoryRepository.findById(itemRequest.getSubCategoryId()).orElseThrow(
                    () -> new BusinessException(BusinessCode.NOT_FOUND_SUB_CATEGORY)
            );
            item.setSubCategory(subCategory);
            return itemMapper.to(itemRepository.save(item));
        } else {
            return null;
        }

    }

    @Override
    public List<ItemResponse> findAll() {
        List<Item> items = itemRepository.findAll();
        return itemMapper.toList(items, itemMapper::to);
    }

    @Override
    public ItemResponse findById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_SUB_CATEGORY)
        );
        return itemMapper.to(item);
    }

    @Override
    public Page<ItemResponse> findBySubCategoryId(Long id, Pageable pageable) {

        Page<Item> items = itemRepository.findBySubCategoryId(id, pageable);
        return items.map(itemMapper::to);

    }

    @Override
    public boolean delete(Long[] ids) {
        for (Long id: ids) {
            Item item = itemRepository.findById(id).orElseThrow(
                    () -> new BusinessException(BusinessCode.NOT_FOUND_ITEM)
            );
            if(item != null) {
                itemRepository.delete(item);
            }
        }
        return true;
    }
}
