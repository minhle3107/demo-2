package com.example.demo2.service;

import com.example.demo2.dto.CommentClientDTO;
import com.example.demo2.dto.CommentDTO;
import com.example.demo2.model.CommentModel;

import java.util.List;

public interface ICommentService extends IBaseService<CommentDTO, CommentModel> {

    List<CommentClientDTO> getAllCommentByPostId(long postId);
}
