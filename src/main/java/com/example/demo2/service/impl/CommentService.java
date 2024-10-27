package com.example.demo2.service.impl;

import com.example.demo2.dto.CommentClientDTO;
import com.example.demo2.dto.CommentDTO;
import com.example.demo2.entity.Comment;
import com.example.demo2.mapper.CommentMapper;
import com.example.demo2.model.CommentModel;
import com.example.demo2.repository.ICommentRepository;
import com.example.demo2.service.ICommentService;
import com.example.demo2.utiles.FileUploadUtils;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository iCommentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ServletContext servletContext;

    private List<CommentDTO> mappingToDto(List<Comment> comments) {

        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment comment : comments) {
            commentDTOList.add(commentMapper.toDto(comment));
        }

        return commentDTOList;
    }

    @Override
    public List<CommentDTO> getAll() {
        return List.of();
    }

    @Override
    public long create(CommentModel commentModel) {

        Comment comment = commentMapper.toEntity(commentModel);

        return iCommentRepository.create(comment);
    }

    public long create(CommentModel commentModel, MultipartFile imageFile) {
        Comment comment = commentMapper.toEntity(commentModel);

        try {
            String imageUrl = FileUploadUtils.uploadFile(servletContext, imageFile);
            comment.setImage_url(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }

        return iCommentRepository.create(comment);
    }

    @Override
    public CommentDTO update(long id, CommentModel commentModel) {


        Comment commentExisted = iCommentRepository.getById(id);

        if (commentExisted == null) {
            throw new RuntimeException("Comment does not exist");
        }

        Comment commentUpdate = commentMapper.toEntity(commentModel);

        return commentMapper.toDto(iCommentRepository.update(id, commentUpdate));
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public CommentDTO getById(long id) {
        return commentMapper.toDto(iCommentRepository.getById(id));
    }

    @Override
    public List<CommentClientDTO> getAllCommentByPostId(long postId) {
        return iCommentRepository.getAllCommentByPostId(postId);
    }
}
