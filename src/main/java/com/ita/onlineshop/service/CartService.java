package com.ita.onlineshop.service;

import com.ita.onlineshop.dao.CartDao;
import com.ita.onlineshop.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    public Cart getCartById(int cartId) {
        return cartDao.getCartById(cartId);
    }
}

