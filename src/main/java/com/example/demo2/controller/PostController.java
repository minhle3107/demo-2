package com.example.demo2.controller;

import com.example.demo2.dto.PaginationPostDetailsDTO;
import com.example.demo2.dto.PostWithComment;
import com.example.demo2.dto.SearchPostDTO;
import com.example.demo2.model.PostModel;
import com.example.demo2.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private IPostService iPostService;

    @PostMapping
    public long create(@RequestBody PostModel postModel) {
        return iPostService.create(postModel);
    }

    @GetMapping("/{id}")
    public PostWithComment getById(@PathVariable long id) {
        return iPostService.getByIdWithComment(id);
    }

//    @GetMapping
//    public List<PostDetailsDTO> getAllPostDetails() {
//        return iPostService.getAllPostDetails();
//    }

    @GetMapping
    public PaginationPostDetailsDTO getAllPostDetails(@ModelAttribute SearchPostDTO searchPostDTO) {
        return iPostService.getAllPostDetails(searchPostDTO);
    }

}
