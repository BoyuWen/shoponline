package com.shoponline.dao;

import com.shoponline.domain.Order;

import java.util.List;


public interface OrderDao {
    public Order getOrder(int userId, int productId, String time);

    public void addOrder(Order order);

    public boolean deleteOrder(int userId,int productId);

    public boolean updateOrder(Order order);

    public List<Order> getOrders(int userId);

    public List<Order> getAllOrders();

    public List<Order> getOrdersByOrderStatus(int orderStatus);

    public boolean getUserProductOrder(int userId,int productId);
}
