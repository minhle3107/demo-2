package com.example.demo2.rowmapper;

import com.example.demo2.entity.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {

        Post post = new Post();

        post.setId(rs.getLong("id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setAuthor_id(rs.getLong("author_id"));
        post.setCreated_at(rs.getTimestamp("created_at"));
        post.setUpdated_at(rs.getTimestamp("updated_at"));
        post.setDeleted_at(rs.getTimestamp("deleted_at"));

        return post;
    }
}
