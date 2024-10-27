package com.example.demo2.mapper;

import com.example.demo2.dto.CommentDTO;
import com.example.demo2.entity.Comment;
import com.example.demo2.model.CommentModel;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentDTO toDto(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .employee_id(comment.getEmployee_id())
                .post_id(comment.getPost_id())
                .parent_id(comment.getParent_id())
                .content(comment.getContent())
                .image_url(comment.getImage_url())
                .created_at(comment.getCreated_at())
                .updated_at(comment.getUpdated_at())
                .deleted_at(comment.getDeleted_at())
                .build();
    }

    public Comment toEntity(CommentModel commentModel) {
        return Comment.builder()
                .id(commentModel.getId())
                .employee_id(commentModel.getEmployee_id())
                .post_id(commentModel.getPost_id())
                .parent_id(commentModel.getParent_id())
                .content(commentModel.getContent())
                .image_url(commentModel.getImage_url())
                .deleted_at(commentModel.getDeleted_at())
                .build();
    }
}
