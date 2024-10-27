package com.example.demo2.controller;

import com.example.demo2.dto.CommentClientDTO;
import com.example.demo2.dto.CommentDTO;
import com.example.demo2.model.CommentModel;
import com.example.demo2.service.ICommentService;
import com.example.demo2.service.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    @Autowired
    private ICommentService iCommentService;

    @Autowired
    private CommentService commentService;

    @PostMapping
    public long create(@ModelAttribute CommentModel commentModel, @RequestParam("image") MultipartFile imageFile) {
        return commentService.create(commentModel, imageFile);
    }

    @PutMapping("/{id}")
    public CommentDTO update(@PathVariable long id, @RequestBody CommentModel commentModel) {
        return iCommentService.update(id, commentModel);
    }

    @DeleteMapping("/{id}")
    public long delete(@PathVariable long id) {
        return iCommentService.delete(id);
    }

    @GetMapping
    public List<CommentDTO> getAll() {
        return iCommentService.getAll();
    }

//    @GetMapping("/{id}")
//    public CommentDTO getById(@PathVariable long id) {
//        return iCommentService.getById(id);
//    }

    @GetMapping("/{postId}")
    public List<CommentClientDTO> getAllCommentByPostId(@PathVariable long postId) {
        return iCommentService.getAllCommentByPostId(postId);
    }

}
