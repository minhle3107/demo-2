package com.example.demo2.repository;

import com.example.demo2.entity.Like;

public interface ILikeRepository extends IBaseRepository<Like> {

    long getByEmployeeIdAndPostId(long employeeId, long postId);
}
