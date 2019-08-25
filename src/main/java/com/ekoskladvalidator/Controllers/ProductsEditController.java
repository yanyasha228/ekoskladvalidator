package com.ekoskladvalidator.Controllers;

import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.Services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products/{id}/edit")
public class ProductsEditController {

    private static final Logger logger = Logger.getLogger(ProductsEditController.class);

    @Autowired
    private ProductService productService;

    @GetMapping
    public String editProduct(Model model, @PathVariable Integer id) {

        Product product = productService.findById(id).orElse(null);

        model.addAttribute("product", product);

        return "editProduct";
    }

    @PostMapping("submit")
    public String editProductSubmit(Model model , @ModelAttribute(name="product") Product product){

        Product appropriateProductFromDB = productService.findById(product.getId()).get();

        appropriateProductFromDB.setUrlForValidating(product.getUrlForValidating());
        appropriateProductFromDB.setCssQueryForValidating(product.getCssQueryForValidating());


        return "products";

    }



}
