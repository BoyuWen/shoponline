package com.shoponline.service;

import com.shoponline.domain.Car;

import java.util.List;


public interface CarService {
    Car getCar(int userId, int productId);

    void addCar(Car car);

    boolean deleteCar(int userId,int productId);

    boolean updateCar(Car car);

    List<Car> getCars(int userId);
}
