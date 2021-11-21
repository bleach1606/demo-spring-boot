package com.example.demo.model.reponse;

import lombok.Data;

import java.util.UUID;

@Data
public class NotificationResponse {
    private UUID targetId;
    private Long timeSend;
    private String titleVi;
    private String titleEn;
    private String descriptionVi;
    private String descriptionEn;
    private String contentVi;
    private String contentEn;
    private String note;
    private Boolean isRead;
}
