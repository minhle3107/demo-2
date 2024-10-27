package com.example.demo2.service;

import com.example.demo2.dto.PostDTO;
import com.example.demo2.dto.PostDetailsDTO;
import com.example.demo2.dto.PostWithComment;
import com.example.demo2.model.PostModel;

import java.util.List;

public interface IPostService extends IBaseService<PostDTO, PostModel> {
    PostWithComment getByIdWithComment(long id);

    List<PostDetailsDTO> getAllPostDetails();

}
