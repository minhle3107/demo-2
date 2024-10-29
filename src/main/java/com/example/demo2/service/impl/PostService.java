package com.example.demo2.service.impl;

import com.example.demo2.dto.*;
import com.example.demo2.entity.Post;
import com.example.demo2.mapper.PostMapper;
import com.example.demo2.model.PostModel;
import com.example.demo2.repository.ICommentRepository;
import com.example.demo2.repository.IPostRepository;
import com.example.demo2.service.IPostService;
import com.example.demo2.utiles.TimestampConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo2.utiles.CommentTreeBuilder.buildCommentTree;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository iPostRepository;

    @Autowired
    private ICommentRepository iCommentRepository;

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<PostDTO> getAll() {
        return List.of();
    }

    @Override
    public long create(PostModel postModel) {

        Post post = postMapper.toEntity(postModel);

        return iPostRepository.create(post);
    }

    @Override
    public PostDTO update(long id, PostModel postModel) {
        return null;
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public PostDTO getById(long id) {
        PostDTO post = postMapper.toDto(iPostRepository.getById(id));
//        List<CommentByPostIdDTO> commentByPostIdDTOList = iCommentRepository.getAllCommentByPostId(id);
//
//        ListCommentByPostDTO listCommentByPostDTO = new ListCommentByPostDTO();
//
//        listCommentByPostDTO.setPostId(post.getId());
//        listCommentByPostDTO.setTitle(post.getTitle());
//        listCommentByPostDTO.setContent(post.getContent());
//        listCommentByPostDTO.setComments(commentByPostIdDTOList);
        return post;
    }

    @Override
    public PostWithComment getByIdWithComment(long id) {
        PostClientDTO post = iPostRepository.getByIdDetails(id);

        LocalDateTime dateTimePost = TimestampConverter.convertToDateTime(post.getCreated_at());
        String formattedDateTimePost = TimestampConverter.formatDateTime(dateTimePost);

        post.setFormattedCreatedAt(formattedDateTimePost);


        List<CommentClientDTO> commentByPostIdDTOList = iCommentRepository.getAllCommentByPostId(id);

        for (CommentClientDTO comment : commentByPostIdDTOList) {
            LocalDateTime dateTime = TimestampConverter.convertToDateTime(comment.getCommentDetails().getCreated_at());
            String formattedDateTime = TimestampConverter.formatDateTime(dateTime);
            comment.getCommentDetails().setFormattedCreatedAt(formattedDateTime);
        }

        List<CommentClientDTO> commentByPostIdDTOListBuildTree = buildCommentTree(commentByPostIdDTOList);

        PostWithComment postWithComment = new PostWithComment();

        postWithComment.setPost(post);
        postWithComment.setComments(commentByPostIdDTOListBuildTree);

        return postWithComment;
    }

    @Override
    public PaginationPostDetailsDTO getAllPostDetails(SearchPostDTO searchPostDTO) {

        int pageSize = searchPostDTO.getPageSize();
        int pageNumber = searchPostDTO.getPageNumber();

        List<PostDetailsDTO> postDetailsDTOList = iPostRepository.getAllPostDetails(searchPostDTO);

        int totalPost = iPostRepository.totalPostByEmployeeId();

        // Số item trong trang hiệnt tại
        int totalItems = postDetailsDTOList.size();

        int totalPage = (int) Math.ceil((double) totalPost / pageSize);

        MetadataPostDTO metadataPostDTO = new MetadataPostDTO();

        metadataPostDTO.setTotalItems(totalPost);
        metadataPostDTO.setTotalPages(totalPage);
        metadataPostDTO.setPageSize(pageSize);
        metadataPostDTO.setCurrentPage(pageNumber);
        metadataPostDTO.setElementsInTheCurrentPage(totalItems);


        for (PostDetailsDTO post : postDetailsDTOList) {
            LocalDateTime dateTime = TimestampConverter.convertToDateTime(post.getCreated_at());
            String formattedDateTime = TimestampConverter.formatDateTime(dateTime);
            post.setFormattedCreatedAt(formattedDateTime);
        }

        PaginationPostDetailsDTO paginationPostDetailsDTO = new PaginationPostDetailsDTO();

        paginationPostDetailsDTO.setPosts(postDetailsDTOList);
        paginationPostDetailsDTO.setMetadata(metadataPostDTO);

        return paginationPostDetailsDTO;
    }
}
