package com.shoponline.service.impl;

import com.shoponline.dao.OrderDao;
import com.shoponline.domain.Order;
import com.shoponline.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public Order getOrder(int userId, int productId, String time) {
        return orderDao.getOrder(userId,productId,time);
    }

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    } //添加订单

    @Override
    public boolean deleteOrder(int userId, int productId) {
        return orderDao.deleteOrder(userId,productId);
    } //删除订单

    @Override
    public boolean updateOrder(Order order) {
        return orderDao.updateOrder(order);
    } //更新订单信息

    @Override
    public List<Order> getUserOrders(int userId) { //获得用户全部订单
        return orderDao.getUserOrders(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    } //获得网站全部订单

    @Override
    public boolean getUserProductOrder(int userId,int productId) { //根据用户和产品ID查找订单
        return orderDao.getUserProductOrder(userId,productId);
    }
}
