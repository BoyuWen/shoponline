package com.shoponline.service.impl;

import com.shoponline.dao.CarDao;
import com.shoponline.domain.Car;
import com.shoponline.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;
    @Override
    public Car getCar(int userId, int productId) {
        return carDao.getCar(userId,productId);
    }

    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    @Override
    public boolean deleteCar(int userId, int productId) {
        return carDao.deleteCar(userId,productId);
    }

    @Override
    public boolean updateCar(Car car) {
        return carDao.updateCar(car);
    }

    @Override
    public List<Car> getCars(int userId) {
        return carDao.getCars(userId);
    }
}
