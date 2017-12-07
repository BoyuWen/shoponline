package com.shoponline.dao.impl;

import com.shoponline.dao.PriceDao;
import com.shoponline.domain.Price;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class PriceDaoImpl implements PriceDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Price getPrice(int userId, int productId, String time) {
        String hql = "from Price where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(0, time);
        return (Price) query.uniqueResult();
    }

    @Override
    public void addPrice(Price price) {
        sessionFactory.getCurrentSession().save(price);
    }

    @Override
    public boolean deletePrice(int userId, int productId, String time) {
        String hql = "delete Price where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updatePrice(Price price) {
        String hql = "update Price set content=? where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, price.getContent());
        query.setParameter(0, price.getUserId());
        query.setParameter(1, price.getProductId());
        query.setParameter(2, price.getTime());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Price> getProductPrice(int productId) {
        String hql = "from Price where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return  query.list();
    }
}
