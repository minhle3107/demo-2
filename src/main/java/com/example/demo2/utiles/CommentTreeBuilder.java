package com.example.demo2.utiles;

import com.example.demo2.dto.CommentClientDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentTreeBuilder {

    public static List<CommentClientDTO> buildCommentTree(List<CommentClientDTO> comments) {
        Map<Long, List<CommentClientDTO>> commentMap = new HashMap<>();

        // Tạo map với key là parentId và value là danh sách các comment con
        for (CommentClientDTO comment : comments) {
            Long parentId = comment.getCommentDetails().getParentId();
            if (!commentMap.containsKey(parentId)) {
                commentMap.put(parentId, new ArrayList<>());
            }
            commentMap.get(parentId).add(comment);
        }

        // Lấy danh sách các comment gốc (root comments)
        List<CommentClientDTO> rootComments = commentMap.get(null);

        if (rootComments != null) {
            for (CommentClientDTO rootComment : rootComments) {
                rootComment.getCommentDetails().setChildComments(getChildCommentList(rootComment.getCommentDetails().getId(), commentMap));
            }
        }

        return rootComments;
    }

    private static List<CommentClientDTO> getChildCommentList(Long parentId, Map<Long, List<CommentClientDTO>> commentMap) {
        List<CommentClientDTO> childComments = commentMap.get(parentId);

        if (childComments != null) {
            for (CommentClientDTO childComment : childComments) {
                childComment.getCommentDetails().setChildComments(getChildCommentList(childComment.getCommentDetails().getId(), commentMap));
            }
        }

        return childComments;
    }
}