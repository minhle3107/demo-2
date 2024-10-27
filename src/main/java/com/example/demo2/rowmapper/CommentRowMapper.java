package com.example.demo2.rowmapper;

import com.example.demo2.entity.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {

        Comment comment = new Comment();

        comment.setId(rs.getLong("id"));
        comment.setEmployee_id(rs.getLong("employee_id"));
        comment.setPost_id(rs.getLong("post_id"));
        comment.setParent_id(rs.getLong("parent_id"));
        comment.setContent(rs.getString("content"));
        comment.setImage_url(rs.getString("image_url"));
        comment.setCreated_at(rs.getTimestamp("created_at"));
        comment.setUpdated_at(rs.getTimestamp("updated_at"));
        comment.setDeleted_at(rs.getTimestamp("deleted_at"));

        return comment;
    }
}
