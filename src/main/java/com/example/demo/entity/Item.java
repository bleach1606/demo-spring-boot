package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "public")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class Item extends BaseModel {

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "price")
    private Double price;

    @ManyToOne()
    private SubCategory subCategory;

}
