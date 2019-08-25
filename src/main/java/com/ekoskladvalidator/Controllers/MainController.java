package com.ekoskladvalidator.Controllers;


import com.ekoskladvalidator.ParseUtils.TestParse;
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
    private TestParse testParse;

    @RequestMapping
    public String mainPage(Model model) {

        return "settingsParse";

    }


    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("parse")
    public String parseSubmit(){
        return "settingsParse";
    }

    @PostMapping("submit")
    public String parseSubmit(@RequestParam String syncUrl) throws IOException {
//        testParse.parseTestEx(syncUrl);

        return testParse.getOneEllem("#product-price-2490 > span");

        //        return "redirect:../";
    }
}
