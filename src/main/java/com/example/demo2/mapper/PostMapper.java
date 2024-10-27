package com.example.demo2.mapper;

import com.example.demo2.dto.PostDTO;
import com.example.demo2.entity.Post;
import com.example.demo2.model.PostModel;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDTO toDto(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author_id(post.getAuthor_id())
                .created_at(post.getCreated_at())
                .updated_at(post.getUpdated_at())
                .deleted_at(post.getDeleted_at())
                .build();
    }

    public Post toEntity(PostModel postModel) {
        return Post.builder()
                .id(postModel.getId())
                .title(postModel.getTitle())
                .content(postModel.getContent())
                .author_id(postModel.getAuthor_id())
                .created_at(postModel.getCreated_at())
                .updated_at(postModel.getUpdated_at())
                .deleted_at(postModel.getDeleted_at())
                .build();
    }

}
