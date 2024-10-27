package com.example.demo2.repository.impl;

import com.example.demo2.dto.CommentClientDTO;
import com.example.demo2.entity.Comment;
import com.example.demo2.mapper.CommentMapper;
import com.example.demo2.repository.ICommentRepository;
import com.example.demo2.rowmapper.CommentByPostRowMapper;
import com.example.demo2.rowmapper.CommentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository implements ICommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getAll() {
        return List.of();
    }

    @Override
    public long create(Comment comment) {

        String query = "INSERT INTO comments (employee_id, post_id, parent_id, content, image_url) VALUES (?, ?, ?, ?, ?)";


        jdbcTemplate.update(query, comment.getEmployee_id(), comment.getPost_id(), comment.getParent_id(), comment.getContent(), comment.getImage_url());
        return 1;
    }

    @Override
    public Comment update(long id, Comment comment) {

        String query = "UPDATE comments set content = ? where id = ?";
        jdbcTemplate.update(query, comment.getContent(), id);

        return getById(id);
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public Comment getById(long id) {

        String query = "SELECT * FROM comments where id = ? and deleted_at is null";

        return jdbcTemplate.queryForObject(query, new CommentRowMapper(), id);
    }


    @Override
    public List<CommentClientDTO> getAllCommentByPostId(long postId) {
        String query = "SELECT c.id as comment_id, p.id as post_id, c.content, c.employee_id, CONCAT(e.first_name, ' ', e.last_name) AS full_name, e.avatar, c.parent_id, c.created_at, c.image_url " +
                "FROM posts p " +
                "JOIN comments c ON c.post_id = p.id " +
                "JOIN employees e ON c.employee_id = e.id " +
                "where p.deleted_at IS NULL and p.id = ? " +
                "ORDER BY c.created_at";

        return jdbcTemplate.query(query, new CommentByPostRowMapper(), postId);
    }
}
