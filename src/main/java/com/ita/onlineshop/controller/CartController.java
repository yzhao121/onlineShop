package com.ita.onlineshop.controller;

import com.ita.onlineshop.entity.Cart;
import com.ita.onlineshop.entity.Customer;
import com.ita.onlineshop.service.CartService;
import com.ita.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {

    // we need to get cart by customer, and then find the corresponding cart
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart/getCartById", method = RequestMethod.GET)
    public ModelAndView getCartId() {
        ModelAndView modelAndView = new ModelAndView("cart");
        // we can use SecurityContextHolder to get the logged user
        // SecurityContextHolder: wrapped in a SpringSecurity filer
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedUser.getName();
        Customer customer = customerService.getCustomer(username);
        modelAndView.addObject("cartId", customer.getCart().getId());
        return modelAndView;
    }

    @RequestMapping(value = "/cart/getCart/{cartId}", method = RequestMethod.GET)
    @ResponseBody
    public Cart getCartItems(@PathVariable(value="cartId")int cartId){
        // @ResponseBody can serialize the data to JSON
        return cartService.getCartById(cartId);
    }

}
