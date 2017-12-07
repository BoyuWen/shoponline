package com.shoponline.service.impl;

import com.shoponline.dao.PriceDao;
import com.shoponline.domain.Price;
import com.shoponline.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceDao priceDao;

    @Override
    public Price getPrice(int userId, int productId, String time) {
        return priceDao.getPrice(userId,productId,time);
    }

    @Override
    public void addPrice(Price price) {
        priceDao.addPrice(price);
    }

    @Override
    public boolean deletePrice(int userId, int productId, String time) {
        return priceDao.deletePrice(userId,productId,time);
    }

    @Override
    public boolean updatePrice(Price price) {
        return priceDao.updatePrice(price);
    }

    @Override
    public List<Price> getProductPrice(int productId) {
        return priceDao.getProductPrice(productId);
    }
}
