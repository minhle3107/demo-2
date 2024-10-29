package com.example.demo2.service;

import com.example.demo2.dto.PaginationPostDetailsDTO;
import com.example.demo2.dto.PostDTO;
import com.example.demo2.dto.PostWithComment;
import com.example.demo2.dto.SearchPostDTO;
import com.example.demo2.model.PostModel;

public interface IPostService extends IBaseService<PostDTO, PostModel> {
    PostWithComment getByIdWithComment(long id);

    PaginationPostDetailsDTO getAllPostDetails(SearchPostDTO searchPostDTO);

}
