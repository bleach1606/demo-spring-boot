package com.example.demo.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;

/**
 * The type Base entity listener.
 */
public class BaseModelListener {

  /**
   * Prepare before create.
   *
   * @param baseModel the base id entity
   */
  @PrePersist
  public void prepareBeforeCreate(BaseModel baseModel) {
    baseModel.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    baseModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    baseModel.setIsDeleted(false);
  }

  /**
   * Prepare before update.
   *
   * @param baseModel the base id entity
   */
  @PreUpdate
  public void prepareBeforeUpdate(BaseModel baseModel) {
    baseModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
  }
}
