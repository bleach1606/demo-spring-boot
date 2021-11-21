package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "notification", schema = "public")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class Notification extends BaseModelUUID {

    @Column(name = "target_id")
    private UUID targetId;

    @Column(name = "time_send")
    private Timestamp timeSend;

    @Column(name = "title_vi")
    private String titleVi;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "description_vi")
    private String descriptionVi;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "content_vi")
    private String contentVi;

    @Column(name = "content_en")
    private String contentEn;

    @Column(name = "note")
    private String note;

}
