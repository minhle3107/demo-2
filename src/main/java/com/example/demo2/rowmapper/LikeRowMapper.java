package com.example.demo2.rowmapper;

import com.example.demo2.entity.Like;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeRowMapper implements RowMapper<Like> {
    @Override
    public Like mapRow(ResultSet rs, int rowNum) throws SQLException {

        Like like = new Like();

        like.setId(rs.getLong("id"));
        like.setEmployee_id(rs.getLong("employee_id"));
        like.setPost_id(rs.getLong("post_id"));

        return like;
    }
}
