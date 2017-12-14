package com.shoponline.service;

import com.shoponline.domain.Order;

import java.util.List;


public interface OrderService {
    Order getOrder(int userId, int productId, String time);

    void addOrder(Order order);

    boolean deleteOrder(int userId,int productId);

    boolean updateOrder(Order order);

    List<Order> getUserOrders(int userId);

    List<Order> getAllOrders();

    boolean getUserProductOrder(int userId,int productId);
}
