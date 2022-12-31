package com.dochero.documentrevisionservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentDTO {
    @NotBlank(message = "UserId could not be blank")
    private String userId;
    @NotBlank(message = "FullName could not be blank")
    private String fullName;
    @NotBlank(message = "Comment content could not be blank")
    private String content;
}
