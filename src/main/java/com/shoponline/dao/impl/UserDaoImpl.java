package com.shoponline.dao.impl;

import com.shoponline.dao.UserDao;
import com.shoponline.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public User getUser(int id) {
        String hql = "from User where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (User)query.uniqueResult();
    }

    @Override
    public User getUser(String UsernameOrEmail) {
        String hql = "from User where email=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, UsernameOrEmail);
        if(query.uniqueResult() == null){
            hql = "from User where username=?";
            query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter(0, UsernameOrEmail);
        }
        return (User)query.uniqueResult();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public boolean deleteUser(int id) {
        String hql = "delete User where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateUser(User user) {
        String hql = "update User set username = ?,password=?,email=?,phone=?,address=?where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,user.getUsername());
        query.setParameter(1,user.getPassword());
        query.setParameter(2,user.getEmail());
        query.setParameter(3,user.getPhone());
        query.setParameter(4,user.getAddress());
        query.setParameter(5,user.getId());
        return query.executeUpdate() > 0;
    }

    @Override
    public List<User> getAllUser() {
        String hql = "from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
