package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@MappedSuperclass
public class BaseModelUUID implements Serializable {

  @Id
  @GeneratedValue
  protected UUID id;

  @Column(name = "created_at")
  protected Timestamp createdAt;

  @Column(name = "updated_at")
  protected Timestamp updatedAt;

  @Column(name = "is_deleted")
  protected Boolean isDeleted;

}
