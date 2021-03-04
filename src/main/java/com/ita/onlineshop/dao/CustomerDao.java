package com.ita.onlineshop.dao;

import com.ita.onlineshop.entity.Authorities;
import com.ita.onlineshop.entity.Customer;
import com.ita.onlineshop.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCustomer(Customer customer) {
        // assign authority
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmailId(customer.getUser().getEmailId());

        // save the customer and corresponding authority into database
        // Session is like an object to connect with db
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Customer getCustomer(String email) {
        User user = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            user = session.get(User.class, email);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
//        try (Session session = sessionFactory.openSession()) {
//            user = session.get(User.class, userName);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        if (user != null) {
            return user.getCustomer();
        }
        return null;
    }
}
