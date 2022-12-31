package com.dochero.documentrevisionservice.dto.request;

import com.dochero.documentrevisionservice.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRevisionRequest {
    private String revisionData;

    @Valid
    private List<Comment> comments;
}
