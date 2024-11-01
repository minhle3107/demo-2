package com.example.demo2.rowmapper;

import com.example.demo2.dto.PostDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDetailsRowMapper implements RowMapper<PostDetailsDTO> {
    @Override
    public PostDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        PostDetailsDTO postDetailsDTO = new PostDetailsDTO();

        postDetailsDTO.setId(rs.getLong("id"));
        postDetailsDTO.setTitle(rs.getString("title"));
        postDetailsDTO.setContent(rs.getString("content"));
        postDetailsDTO.setAuthor_name(rs.getString("author_name"));
        postDetailsDTO.setAuthor_id(rs.getLong("author_id"));
        postDetailsDTO.setAvatar(rs.getString("avatar"));
        postDetailsDTO.setCreated_at(rs.getTimestamp("created_at"));
        postDetailsDTO.setTotal_likes(rs.getInt("total_likes"));
        postDetailsDTO.setTotal_comments(rs.getInt("total_comments"));
        postDetailsDTO.setLiked_by_employee_1(rs.getInt("liked_by_employee_1"));

        return postDetailsDTO;
    }
}
