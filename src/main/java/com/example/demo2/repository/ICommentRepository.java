package com.example.demo2.repository;

import com.example.demo2.dto.CommentClientDTO;
import com.example.demo2.entity.Comment;

import java.util.List;

public interface ICommentRepository extends IBaseRepository<Comment> {

    List<CommentClientDTO> getAllCommentByPostId(long postId);

}
