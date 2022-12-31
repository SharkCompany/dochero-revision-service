package com.dochero.documentrevisionservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Comment implements Serializable {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("content")
    private String content;
    @JsonProperty("createdAt")
    private Timestamp createdAt;
}
