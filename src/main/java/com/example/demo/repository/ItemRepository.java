package com.example.demo.repository;

import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> getItemByPriceBetween(float value1, float value2);
    List<Item> getItemByCreatedAtBetween(Timestamp timeStart, Timestamp timeEnd);
}
