package com.shoponline.dao.impl;

import com.shoponline.dao.CarDao;
import com.shoponline.domain.Car;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class CarDaoImpl implements CarDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Car getCar(int userId, int productId) {
        String hql = "from Car where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return (Car) query.uniqueResult();
    }

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public boolean deleteCar(int userId, int productId) {
        String hql = "delete Car where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateCar(Car car) {
        String hql = "update Car set productPrice=?,counts=? where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, car.getProductPrice());
        query.setParameter(1, car.getCounts());
        query.setParameter(2, car.getUserId());
        query.setParameter(3, car.getProductId());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Car> getCars(int userId) {
        String hql = "from Car where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        return query.list();
    }
}
