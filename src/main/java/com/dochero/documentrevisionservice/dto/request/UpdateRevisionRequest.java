package com.dochero.documentrevisionservice.dto.request;

import com.dochero.documentrevisionservice.dto.CommentDTO;
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
    private List<CommentDTO> comments;
}
