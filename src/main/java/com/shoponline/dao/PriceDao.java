package com.shoponline.dao;

import com.shoponline.domain.Price;

import java.util.List;


public interface PriceDao {
    public Price getPrice(int userId, int productId, String time);

    public void addPrice(Price price);

    public boolean deletePrice(int userId,int productId,String time);

    public boolean updatePrice(Price price);

    public List<Price> getProductPrice(int productId);
}
