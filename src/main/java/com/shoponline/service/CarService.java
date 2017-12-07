package com.shoponline.service;

import com.shoponline.domain.Car;

import java.util.List;


public interface CarService {
    public Car getCar(int userId, int productId);

    public void addCar(Car car);

    public boolean deleteCar(int userId,int productId);

    public boolean updateCar(Car car);

    public List<Car> getCars(int userId);
}
