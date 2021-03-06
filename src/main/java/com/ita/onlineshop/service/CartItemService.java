package com.ita.onlineshop.service;

import com.ita.onlineshop.dao.CartItemDao;
import com.ita.onlineshop.entity.Cart;
import com.ita.onlineshop.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartItemService {

    @Autowired
    private CartItemDao cartItemDao;

    public void addCartItem(CartItem cartItem) {
        cartItemDao.addCartItem(cartItem);

    }

    public void removeCartItem(int cartItemId) {
        cartItemDao.removeCartItem(cartItemId);
    }

    public void removeAllCartItems(Cart cart) {
        cartItemDao.removeAllCartItems(cart);
    }
}

