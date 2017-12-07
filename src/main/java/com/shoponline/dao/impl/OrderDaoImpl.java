package com.shoponline.dao.impl;

import com.shoponline.dao.OrderDao;
import com.shoponline.domain.Order;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class OrderDaoImpl implements OrderDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Order getOrder(int userId, int productId, String time) {
        String hql = "from Order where userId=? and productId=? and time=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        query.setParameter(2, time);
        return (Order) query.uniqueResult();
    }

    @Override
    public void addOrder(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public boolean deleteOrder(int userId, int productId) {
        String hql = "delete Order where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        String hql = "update ShoponlineReocrd set orderStatus=? where userId=? and productId=? and time=?";
        String sql = "update record set order_status="+ order.getOrderStatus()+" where user_id="+ order.getUserId()+" and product_id="+ order.getProductId()+" and time='"+ order.getTime()+"'";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Order> getOrders(int userId) {
        String hql = "from Order where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        return query.list();
    }

    @Override
    public List<Order> getAllOrders() {
        String hql = "from Order";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<Order> getOrdersByOrderStatus(int orderStatus) {
        String hql = "from Order where orderStatus=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,orderStatus);
        return query.list();
    }

    @Override
    public boolean getUserProductOrder(int userId,int productId) {
        String hql = "from Order where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        query.setParameter(1,productId);
        return query.list().size()>0;
    }
}
