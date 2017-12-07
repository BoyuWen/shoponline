package com.shoponline.dao;

import com.shoponline.domain.Car;

import java.util.List;

public interface CarDao {
    public Car getCar(int userId, int productId);

    public void addCar(Car car);

    public boolean deleteCar(int userId,int productId);

    public boolean updateCar(Car car);

    public List<Car> getCars(int userId);
}
