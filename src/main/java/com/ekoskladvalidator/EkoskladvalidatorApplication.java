package com.ekoskladvalidator;

import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.RestServices.ProductRestService;
import com.ekoskladvalidator.Services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
public class EkoskladvalidatorApplication {

	private static final Logger log = LoggerFactory.getLogger(EkoskladvalidatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EkoskladvalidatorApplication.class, args);
	}

	@Bean("CRunner")
	public CommandLineRunner testDataProd(ProductService productService ,
										  ProductRestService productRestService) {
		return (args) -> {

			productService.save(productRestService.getAll());

            List<Product> productList = productService.findAll();

            for (Product pr : productList){
            	log.info(pr.getName());
				log.info(String.valueOf(pr.getId()));
				log.info(pr.getCurrency());
				log.info(pr.getStatus().toString());
				log.info(pr.getMain_image());
				log.info(pr.getLastValidationDate().toString());
				log.info(pr.getCssQueryForValidating());
				log.info(pr.getUrlForValidating());
				log.info(String.valueOf(pr.getGroup().getId()));
				log.info(pr.getGroup().getName());


			}
//            List<Client> clientsFDB = clientService.findAll();
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Client cProd : clientsFDB) {
//                log.info(cProd.toString());
//                log.info(cProd.getPhoneNumber().getPhoneNumber());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            clientService.findById(1L)
//                    .ifPresent(cProd -> {
//                        log.info("Customer found with findById(1L):");
//                        log.info("--------------------------------");
//                        log.info(cProd.toString());
//                        log.info("");
//                    });
//
//            // fetch customers by last name
////            log.info("Customer found with findByLastName('Bauer'):");
////            log.info("--------------------------------------------");
////            productService.findByProductName("Hatsan 125 TH").ifPresent(prodBN -> {
////                log.info(prodBN.toString());
////            });
////            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            // 	log.info(bauer.toString());
//            // }
//            log.info("");
			};
		};
	}

