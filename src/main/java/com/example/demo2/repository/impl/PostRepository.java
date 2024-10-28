package com.example.demo2.repository.impl;

import com.example.demo2.dto.PostClientDTO;
import com.example.demo2.dto.PostDetailsDTO;
import com.example.demo2.entity.Post;
import com.example.demo2.repository.IPostRepository;
import com.example.demo2.rowmapper.PostClientRowMapper;
import com.example.demo2.rowmapper.PostDetailsRowMapper;
import com.example.demo2.rowmapper.PostRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostRepository implements IPostRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Post> getAll() {
        return List.of();
    }


    @Override
    public long create(Post post) {

//        Link stackoverflow: https://stackoverflow.com/questions/14537546/how-to-get-generated-id-after-i-inserted-into-a-new-data-record-in-database-usin
        String query = "INSERT INTO posts(content, author_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
                ps.setString(1, post.getContent());
                ps.setLong(2, post.getAuthor_id());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Post update(long id, Post post) {
        return null;
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public Post getById(long id) {
        String query = "SELECT * FROM posts where id = ? AND deleted_at IS NULL";

        return jdbcTemplate.queryForObject(query, new PostRowMapper(), id);
    }


    @Override
    public PostClientDTO getByIdDetails(long id) {
//        String query = "SELECT p.id, p.title, p.content, CONCAT(e.first_name, ' ', e.last_name) " +
//                "AS author_name, p.created_at, e.avatar, p.author_id " +
//                "FROM posts p " +
//                "JOIN employees e ON e.id = p.author_id " +
//                "WHERE p.id = ? AND p.deleted_at IS NULL";

        String query = "SELECT p.id, p.title, p.content, CONCAT(e.first_name, ' ', e.last_name) " +
                "AS author_name, p.created_at, e.avatar, p.author_id, " +
                "COUNT(DISTINCT l.id) AS total_likes, " +
                "COUNT(DISTINCT c.id) AS total_comments, " +
                "COUNT(DISTINCT CASE WHEN l.employee_id = 1 THEN l.id END) AS liked_by_employee_1 " +
                "FROM posts p " +
                "JOIN employees e ON e.id = p.author_id " +
                "LEFT JOIN comments c ON c.post_id = p.id " +
                "LEFT JOIN likes l ON l.post_id = p.id " +
                "WHERE p.deleted_at IS NULL AND p.id = ? " +
                "GROUP BY p.id, p.title, p.content, e.first_name, e.last_name, p.created_at, e.avatar " +
                "order by p.created_at DESC";

        return jdbcTemplate.queryForObject(query, new PostClientRowMapper(), id);
    }

    @Override
    public List<PostDetailsDTO> getAllPostDetails() {

        String query = "SELECT p.id, p.title, p.content, CONCAT(e.first_name, ' ', e.last_name) " +
                "AS author_name, p.created_at, e.avatar, p.author_id, " +
                "COUNT(DISTINCT l.id) AS total_likes, " +
                "COUNT(DISTINCT c.id) AS total_comments, " +
                "COUNT(DISTINCT CASE WHEN l.employee_id = 1 THEN l.id END) AS liked_by_employee_1 " +
                "FROM posts p " +
                "JOIN employees e ON e.id = p.author_id " +
                "LEFT JOIN comments c ON c.post_id = p.id " +
                "LEFT JOIN likes l ON l.post_id = p.id " +
                "WHERE p.deleted_at IS NULL " +
                "GROUP BY p.id, p.title, p.content, e.first_name, e.last_name, p.created_at, e.avatar " +
                "order by p.created_at DESC";

        return jdbcTemplate.query(query, new PostDetailsRowMapper());

    }
}
