package com.example.demo2.rowmapper;

import com.example.demo2.dto.AccountDTO;
import com.example.demo2.dto.CommentClientDTO;
import com.example.demo2.dto.CommentDetailsClientDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentByPostRowMapper implements RowMapper<CommentClientDTO> {
    @Override
    public CommentClientDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        CommentClientDTO listCommentByPostDTO = new CommentClientDTO();

        AccountDTO accountComment = new AccountDTO();

        accountComment.setEmployee_id(rs.getLong("employee_id"));
        accountComment.setFull_name(rs.getString("full_name"));
        accountComment.setAvatar(rs.getString("avatar"));

        CommentDetailsClientDTO comment = new CommentDetailsClientDTO();
        comment.setId(rs.getLong("comment_id"));
        comment.setPostId(rs.getLong("post_id"));

        Long parentId = rs.getLong("parent_id");
        if (rs.wasNull()) {
            parentId = null;
        }
        comment.setParentId(parentId);

        comment.setContent(rs.getString("content"));
        comment.setImageUrl(rs.getString("image_url"));
        comment.setCreated_at(rs.getTimestamp("created_at"));

        // account
        listCommentByPostDTO.setAccount(accountComment);

        // Comment:
        listCommentByPostDTO.setCommentDetails(comment);


        return listCommentByPostDTO;
    }
}