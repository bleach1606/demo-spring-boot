package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@MappedSuperclass
public class BaseModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  @EqualsAndHashCode.Include
  protected Long id;

  @Column(name = "created_at")
  protected Timestamp createdAt;

  @Column(name = "updated_at")
  protected Timestamp updatedAt;

  @Column(name = "is_deleted")
  protected Boolean isDeleted;

}
