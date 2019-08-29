package com.ekoskladvalidator.Controllers;


import com.ekoskladvalidator.ParseUtils.CssQueryParser;
import com.ekoskladvalidator.Validators.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ProductValidator productValidator;

    @RequestMapping
    public String mainPage(Model model) {

        return "redirect:/products";

    }

    @GetMapping("sync")
    public String sync(Model model) {

        productValidator.validateProducts();

        return "redirect:/products";

    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }




}
