package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface CategoryRepository extends JpaRepositoryImplementation<Category, Long> {

    @Query( value = "select c from Category c " +
            "where 1 = 1 " +
            "and lower(c.code) like :code " +
            "and lower(c.name) like :name"
    )
    Page<Category> findAllByFilter2(String name, String code, Pageable pageable);
}
