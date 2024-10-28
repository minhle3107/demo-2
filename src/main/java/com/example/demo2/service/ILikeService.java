package com.example.demo2.service;

import com.example.demo2.dto.LikeDTO;
import com.example.demo2.model.LikeModel;

public interface ILikeService extends IBaseService<LikeDTO, LikeModel> {

    LikeDTO getByEmployeeIdAndPostId(long employeeId, long postId);

    long unLike(long employeeId, long postId);
}
