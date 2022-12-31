package com.dochero.documentrevisionservice.utils;

import com.dochero.documentrevisionservice.dto.CommentDTO;
import com.dochero.documentrevisionservice.entity.Comment;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class CommentMapperUtils {

    public static Comment convertCommentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setFullName(commentDTO.getFullName());
        comment.setUserId(commentDTO.getUserId());
        comment.setCreatedAt(Timestamp.from(Instant.now()));
        return comment;
    }

    public static List<Comment> convertListCommentDTOsToListComments(List<CommentDTO> commentDTOs) {
        return commentDTOs.stream().map(CommentMapperUtils::convertCommentDTOToComment).collect(Collectors.toList());
    }
}
