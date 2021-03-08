package com.ita.onlineshop.dao;

import com.ita.onlineshop.entity.Cart;
import com.ita.onlineshop.entity.CartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Cart getCartById(int cartId) {
        Cart cart = null;
        try (Session session = sessionFactory.openSession()) {
            cart = session.get(Cart.class, cartId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    public Cart validate(int cartId) throws IOException {
        Cart cart = getCartById(cartId);
        if (cart == null || cart.getCartItem().size() == 0) {
            throw new IOException(cartId + "");
        }
        update(cart);
        return cart;
    }

    private void update(Cart cart) {
        double total = getSalesOrderTotal(cart);
        cart.setTotalPrice(total);

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(cart);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double getSalesOrderTotal(Cart cart) {
        double total = 0;
        List<CartItem> cartItems = cart.getCartItem();

        for (CartItem item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }

}
