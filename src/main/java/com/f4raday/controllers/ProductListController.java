package com.f4raday.controllers;

import com.f4raday.model.Product;
import com.f4raday.service.interfaces.IProductService;
import com.f4raday.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ProductListController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model)
    {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null || context.getAuthentication().getPrincipal().equals("anonymousUser"))
            return "redirect:/login";

        model.addAttribute("productList", productService.findAll());
        model.addAttribute("product", new Product());

        return "hello";
    }

    @RequestMapping(value = "/sendProduct", method = RequestMethod.POST)
    public String hello(@ModelAttribute("product") Product product, BindingResult result, Model model)
    {
        model.addAttribute("lastProduct", product);
        productService.delete(product.getId());


        return "sentProduct";
    }

    @RequestMapping(value = "newProduct", method = RequestMethod.GET)
    public String newProduct (Model model) {
        model.addAttribute("product", new Product());

        return "newProduct";
    }

    @RequestMapping(value = "addNewProduct", method = RequestMethod.POST)
    public String newProduct (@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        productValidator.validate(product, bindingResult);

        if(bindingResult.hasErrors()) {
            return "newProduct";
        }

        productService.save(product);
        model.addAttribute("newProduct", product);

        return "newProductSuccess";
    }
}
