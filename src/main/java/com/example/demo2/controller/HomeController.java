package com.example.demo2.controller;

import com.example.demo2.model.CommentModel;
import com.example.demo2.repository.IEmployeeRepository;
import com.example.demo2.service.ICommentService;
import com.example.demo2.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1")
public class HomeController {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IPostService iPostService;

    @Autowired
    private ICommentService iCommentService;

//    public HomeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }


    @GetMapping("/posts/{id}")
    public String getById(@PathVariable long id, Model model) {
//        CommentModel commentModel = new CommentModel();
//
//        ListCommentByPostDTO post = iPostService.getByIdWithComment(id);
//        model.addAttribute("post", post);
//
//        Long postId = post.getPostId();
//
//        model.addAttribute("commentModel", commentModel);
//        model.addAttribute("postId", postId);

        return "post-details";
    }

    @GetMapping("/posts")
    public String getAllPostDetails(Model model) {
//        List<PostDetailsDTO> listPost = iPostService.getAllPostDetails();
//        model.addAttribute("listPost", listPost);
        return "index";
    }


    @PostMapping("/posts/{id}")
    public String createCommentRoot(@PathVariable long id, @ModelAttribute CommentModel commentModel) {

        Long postId = id;
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


    @PostMapping("/posts-reply-comment/{id}")
    public String createCommentChild(@PathVariable long id, @RequestBody CommentModel commentModel) {

        Long postId = id;
//        Long employeeId = 2L;

        String content = commentModel.getContent();

        Long parent_id = commentModel.getParent_id();

        Long employeeId = commentModel.getEmployee_id();

        CommentModel commentModel1 = new CommentModel();
        commentModel1.setEmployee_id(employeeId);
        commentModel1.setPost_id(postId);
        commentModel1.setContent(content);
        commentModel1.setParent_id(parent_id);

        Long result = iCommentService.create(commentModel1);

        return "redirect:/v1/posts/" + postId;


    }
}
