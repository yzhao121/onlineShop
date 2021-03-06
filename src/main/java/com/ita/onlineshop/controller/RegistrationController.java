package com.ita.onlineshop.controller;

import com.ita.onlineshop.entity.Customer;
import com.ita.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm() {
        // modelName对应regist form(register.jsp)中的modelAttribute
        return new ModelAndView("register", "customer", new Customer());
    }
    @RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
    public ModelAndView registerCustomer(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        customerService.addCustomer(customer);
        modelAndView.setViewName("login");
        modelAndView.addObject("RegistrationSuccess", "Registered Successfully. Login using username and password");
        return modelAndView;
    }
}
