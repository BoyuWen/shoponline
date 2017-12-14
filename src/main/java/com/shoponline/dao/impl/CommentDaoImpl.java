package com.shoponline.dao.impl;

import com.shoponline.dao.CommentDao;
import com.shoponline.domain.Comment;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class CommentDaoImpl implements CommentDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Comment getComment(int userId, int productId, String time) {
        String hql = "from Comment where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return (Comment) query.uniqueResult();
    }

    @Override
    public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }

    @Override
    public boolean deleteComment(int userId, int productId, String time) {
        String hql = "delete Comment where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateComment(Comment comment) {
        String hql = "update Comment set content=? where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, comment.getContent());
        query.setParameter(1, comment.getUserId());
        query.setParameter(2, comment.getProductId());
        query.setParameter(3, comment.getTime());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Comment> getComments(int productId) {
        String hql = "from Comment where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return  query.list();
    }
}
