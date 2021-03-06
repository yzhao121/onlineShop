package com.ita.onlineshop.controller;

import com.ita.onlineshop.entity.Cart;
import com.ita.onlineshop.entity.CartItem;
import com.ita.onlineshop.entity.Customer;
import com.ita.onlineshop.entity.Product;
import com.ita.onlineshop.service.CartItemService;
import com.ita.onlineshop.service.CartService;
import com.ita.onlineshop.service.CustomerService;
import com.ita.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class CartItemController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/cart/add/{productId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addCartItem(@PathVariable(value = "productId") int productId) {
        // a method with void return type
        // @ResponseStatus to check whether the operation is success
        // get the customer first
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);

        // get the cart by customer
        Cart cart = customer.getCart();
        List<CartItem> cartItems = cart.getCartItem();
        Product product = productService.getProductById(productId);

        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            if (product.getId() == (cartItem.getProduct().getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getProductPrice());
                cartItemService.addCartItem(cartItem);
                return;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setProduct(product);
        cartItem.setPrice(product.getProductPrice());
        cartItem.setCart(cart);
        cartItemService.addCartItem(cartItem);
    }

    @RequestMapping(value = "/cart/removeCartItem/{cartItemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartItem(@PathVariable(value = "cartItemId") int cartItemId) {
        cartItemService.removeCartItem(cartItemId);
    }

    @RequestMapping(value = "/cart/removeAllItems/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeAllCartItems(@PathVariable(value = "cartId") int cartId) {
        Cart cart = cartService.getCartById(cartId);
        cartItemService.removeAllCartItems(cart);
    }

}
