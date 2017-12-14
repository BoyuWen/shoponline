package com.shoponline.service;

import com.shoponline.domain.Comment;

import java.util.List;


public interface CommentService {
    Comment getComment(int userId, int productId, String time);

    void addComment(Comment comment);

    boolean deleteComment(int userId,int productId,String time);

    boolean updateComment(Comment comment);

    List<Comment> getComments(int productId);
}
