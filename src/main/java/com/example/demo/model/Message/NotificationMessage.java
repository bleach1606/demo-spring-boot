package com.example.demo.model.Message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class NotificationMessage {

    private UUID targetId;
    private Timestamp timeSend;
    private String titleVi;
    private String titleEn;
    private String descriptionVi;
    private String descriptionEn;
    private String contentVi;
    private String contentEn;
    private String note;

//    @JsonProperty("users")
//    private List<UserMessage> users = Lists.newArrayList();
}
