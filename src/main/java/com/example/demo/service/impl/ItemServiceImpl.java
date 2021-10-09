package com.example.demo.service.impl;

import com.example.demo.entity.Item;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.model.reponse.response.ItemResponse;
import com.example.demo.model.request.request.ItemRequest;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemResponse save(ItemRequest itemRequest) {
       Item item = itemMapper.to(itemRequest);
       return itemMapper.to(itemRepository.saveAndFlush(item));
    }

    @Override
    public ItemResponse findById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_ITEM)
        );
        return itemMapper.to(item);
    }

    @Override
    public String deleteById(Long id) {
        itemRepository.deleteById(id);
        return "Ok";
    }

    @Override
    public List<ItemResponse> getAllItem() {
        List<Item> itemList = itemRepository.findAll();
        return itemList.stream().map(itemMapper::to).collect(Collectors.toList());
    }
}
