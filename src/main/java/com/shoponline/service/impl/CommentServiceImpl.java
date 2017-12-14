package com.shoponline.service.impl;

import com.shoponline.dao.CommentDao;
import com.shoponline.domain.Comment;
import com.shoponline.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment getComment(int userId, int productId, String time) {
        return commentDao.getComment(userId,productId,time);
    }

    @Override
    public void addComment(Comment comment) {
        commentDao.addComment(comment);
    }

    @Override
    public boolean deleteComment(int userId, int productId, String time) {
        return commentDao.deleteComment(userId,productId,time);
    }

    @Override
    public boolean updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    @Override
    public List<Comment> getComments(int productId) {
        return commentDao.getComments(productId);
    }
}
