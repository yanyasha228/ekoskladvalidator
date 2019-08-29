package com.ekoskladvalidator;

import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.RestServices.ProductRestService;
import com.ekoskladvalidator.Services.ProductService;
import com.ekoskladvalidator.SyncUtils.DbRestSynchronizer;
import com.ekoskladvalidator.Validators.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.ObjectError;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.ekoskladvalidator"})
public class EkoskladvalidatorApplication {

    private static final Logger log = LoggerFactory.getLogger(EkoskladvalidatorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EkoskladvalidatorApplication.class, args);
    }

}

