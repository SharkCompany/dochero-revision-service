package com.dochero.documentrevisionservice.dto.request;

import com.dochero.documentrevisionservice.dto.CommentDTO;
import lombok.Data;

import java.util.List;

@Data
public class UpdateRevisionRequest {
    private String revisionData;
    private List<CommentDTO> comments;
}
