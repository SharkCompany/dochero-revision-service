package com.dochero.documentrevisionservice.utils;

import com.dochero.documentrevisionservice.dto.CommentDTO;
import com.dochero.documentrevisionservice.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapperUtils {

    public static Comment mapCommentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCreatedBy(commentDTO.getCreatedBy());
        return comment;
    }

    public static List<Comment> mapListCommentDTOsToListComments(List<CommentDTO> commentDTOs) {
        return commentDTOs.stream().map(CommentMapperUtils::mapCommentDTOToComment).collect(Collectors.toList());
    }
}
