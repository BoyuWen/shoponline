package com.shoponline.dao;

import com.shoponline.domain.Comment;

import java.util.List;


public interface CommentDao {
    Comment getComment(int userId, int productId, String time);

    void addComment(Comment comment);

    boolean deleteComment(int userId,int productId,String time);

    boolean updateComment(Comment comment);

    List<Comment> getComments(int productId);
}
