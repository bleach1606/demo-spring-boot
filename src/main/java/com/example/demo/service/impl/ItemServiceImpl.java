package com.example.demo.service.impl;

import com.example.demo.entity.Item;
import com.example.demo.entity.SubCategory;
import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.model.reponse.response.ItemResponse;
import com.example.demo.model.request.request.ItemRequest;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public ItemResponse updateById(Long id, ItemRequest itemRequest) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(itemRequest.getName());
                    item.setPrice(itemRequest.getPrice());
                    SubCategory subCategory = new SubCategory();
                    subCategory.setId(itemRequest.getSub_category_id());
                    item.setSubCategory(subCategory);
                    return itemMapper.to(itemRepository.saveAndFlush(item));
                })
                .orElseGet(() -> {
                    Item item = itemMapper.to(itemRequest);
                    item.setId(id);
                    return itemMapper.to(itemRepository.saveAndFlush(item));
                });
    }


    @Override
    public ItemResponse findById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_ITEM)
        );
        return itemMapper.to(item);
    }

    @Override
    public List<ItemResponse> getItemByPriceBetween(float minPrice, float maxPrice) {
        List<Item> itemList = itemRepository.getItemByPriceBetween(minPrice,maxPrice);
        if (itemList.size() != 0) {
            return itemList.stream().map(itemMapper::to).collect(Collectors.toList());
        } else
            throw (new BusinessException(BusinessCode.NOT_FOUND_ITEM));
    }

    private Date addDays(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1); //minus number would decrement the days
        return cal.getTime();
    }

    @SneakyThrows
    @Override
    public List<ItemResponse> getItemByDateCreated(String srcDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(srcDate);
        List<Item> itemList = itemRepository.getItemByCreatedAtBetween(new Timestamp(date.getTime()), new Timestamp(addDays(date).getTime()));
        if (itemList.size() != 0) {
            return itemList.stream().map(itemMapper::to).collect(Collectors.toList());
        } else
            throw (new BusinessException(BusinessCode.NOT_FOUND_ITEM));
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
