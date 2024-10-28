package com.example.demo2.repository.impl;

import com.example.demo2.entity.Like;
import com.example.demo2.mapper.LikeMapper;
import com.example.demo2.repository.ILikeRepository;
import com.example.demo2.rowmapper.LikeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeRepository implements ILikeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public List<Like> getAll() {
        return List.of();
    }

    @Override
    public long create(Like like) {

        String sql = "INSERT INTO likes(employee_id, post_id) VALUES (?, ?)";

        return jdbcTemplate.update(sql, like.getEmployee_id(), like.getPost_id());
    }

    @Override
    public Like update(long id, Like like) {
        return null;
    }

    @Override
    public long delete(long id) {

        String sql = "DELETE FROM likes WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Like getById(long id) {


        return null;
    }

    @Override
    public Like getByEmployeeIdAndPostId(long employeeId, long postId) {

        String sql = "SELECT * FROM likes l WHERE l.employee_id = ? AND l.post_id = ?";

        return jdbcTemplate.queryForObject(sql, new LikeRowMapper(), employeeId, postId);
    }
}
