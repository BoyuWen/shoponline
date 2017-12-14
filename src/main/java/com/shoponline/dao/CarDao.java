package com.shoponline.dao;

import com.shoponline.domain.Car;

import java.util.List;

public interface CarDao {
    Car getCar(int userId, int productId);

    void addCar(Car car);

    boolean deleteCar(int userId,int productId);

    boolean updateCar(Car car);

    List<Car> getCars(int userId);
}
