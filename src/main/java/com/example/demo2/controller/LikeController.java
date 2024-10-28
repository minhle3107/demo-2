package com.example.demo2.controller;

import com.example.demo2.model.LikeModel;
import com.example.demo2.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class LikeController {

    @Autowired
    private ILikeService iLikeService;

    @PostMapping("/likes")
    public long createLike(@RequestBody LikeModel likeModel) {
        return iLikeService.create(likeModel);
    }

    @PostMapping("/unlikes")
    public long unLike(@RequestBody LikeModel likeModel) {
        return iLikeService.unLike(likeModel);
    }
}
