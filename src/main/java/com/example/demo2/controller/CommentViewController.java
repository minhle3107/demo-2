package com.example.demo2.controller;

import com.example.demo2.model.CommentModel;
import com.example.demo2.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1")
public class CommentViewController {

    @Autowired
    private ICommentService iCommentService;

    @PostMapping("/comments")
    public String createCommentRoot(@ModelAttribute CommentModel commentModel) {

        Long postId = commentModel.getPost_id();
        Long employeeId = 2L;

        String content = commentModel.getContent();

//        Long parent_id = null;

        CommentModel commentModel1 = new CommentModel();
        commentModel1.setEmployee_id(employeeId);
        commentModel1.setPost_id(postId);
        commentModel1.setContent(content);
//        commentModel1.setParent_id(parent_id);

        Long result = iCommentService.create(commentModel1);

        return "redirect:/v1/posts/" + postId;


    }

}
