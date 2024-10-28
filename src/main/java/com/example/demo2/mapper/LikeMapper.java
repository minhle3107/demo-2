package com.example.demo2.mapper;

import com.example.demo2.dto.LikeDTO;
import com.example.demo2.entity.Like;
import com.example.demo2.model.LikeModel;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {
    public LikeDTO toDto(Like like) {
        return LikeDTO.builder()
                .id(like.getId())
                .employee_id(like.getEmployee_id())
                .post_id(like.getPost_id())
                .build();
    }

    public Like toEntity(LikeModel likeModel) {
        return Like.builder()
                .employee_id(likeModel.getEmployee_id())
                .post_id(likeModel.getPost_id())
                .build();
    }
}
