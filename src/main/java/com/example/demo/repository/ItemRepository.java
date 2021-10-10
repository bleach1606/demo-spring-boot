package com.example.demo.repository;

import com.example.demo.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findBySubCategoryId(Long subCategoryId, Pageable pageable);
}
