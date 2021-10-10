package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "sub_category", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = BaseModelListener.class)
public class SubCategory extends BaseModel {

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
