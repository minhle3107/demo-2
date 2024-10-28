package com.example.demo2.rowmapper;

import com.example.demo2.dto.AuthorDTO;
import com.example.demo2.dto.PostClientDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostClientRowMapper implements RowMapper<PostClientDTO> {


    @Override
    public PostClientDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        PostClientDTO postClientDTO = new PostClientDTO();

        postClientDTO.setId(rs.getLong("id"));
        postClientDTO.setContent(rs.getString("content"));

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setAuthorId(rs.getLong("author_id"));
        authorDTO.setAuthorName(rs.getString("author_name"));
        authorDTO.setAvatar(rs.getString("avatar"));

        postClientDTO.setAuthorInfo(authorDTO);

        postClientDTO.setCreated_at(rs.getTimestamp("created_at"));
        postClientDTO.setTotal_likes(rs.getInt("total_likes"));
        postClientDTO.setTotal_comments(rs.getInt("total_comments"));
        postClientDTO.setLiked_by_employee_1(rs.getInt("liked_by_employee_1"));


        return postClientDTO;
    }
}
