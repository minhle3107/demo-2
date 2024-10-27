package com.example.demo2.repository;

import com.example.demo2.dto.PostClientDTO;
import com.example.demo2.dto.PostDetailsDTO;
import com.example.demo2.entity.Post;

import java.util.List;

public interface IPostRepository extends IBaseRepository<Post> {

    PostClientDTO getByIdDetails(long id);

    List<PostDetailsDTO> getAllPostDetails();
}
