package com.ekoskladvalidator.Validators;

import com.ekoskladvalidator.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.ParseUtils.CssQueryParser;
import com.ekoskladvalidator.RestServices.ProductRestService;
import com.ekoskladvalidator.Services.ProductService;
import com.ekoskladvalidator.SyncUtils.DbRestSynchronizer;
import com.ekoskladvalidator.Validators.ValidatorUtils.ProductValidatorUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Component
public class ProductValidator {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRestService productRestService;

    @Autowired
    private DbRestSynchronizer dbRestSynchronizer;

    @Autowired
    private CssQueryParser cssQueryParser;

    @Autowired
    private ProductValidatorUtils priceValidatorUtils;


    @Scheduled(fixedDelay = 10000)
    public void validateProducts(){

        List<Product> syncProductList = dbRestSynchronizer.synchronizeDbProductsWithRestApiModels();

        List<Product> productListForExSave = productService.findAll().stream().
                filter(product -> {

                    boolean ndToAdd = true;

                    for (Product syncProduct : syncProductList) {
                        if (syncProduct.getId() == product.getId()) {
                            ndToAdd = false;
                            break;
                        }
                    }

                    if(!product.isDataForValidatingExist()) ndToAdd = true;

                    return ndToAdd;
                }).
                collect(Collectors.toList()).stream().map(product -> {
            product.setValidationStatus(false);
            return product;
        }).collect(Collectors.toList());

        List<Product> productListForValidation = productService.findAll().stream().
                filter(Product::isDataForValidatingExist).
                filter(product -> {
                    for (Product syncProduct : syncProductList) {
                        if (syncProduct.getId() == product.getId()) return true;
                    }
                    return false;
                }).
                collect(Collectors.toList()).stream().map(product -> {
            product.setValidationStatus(false);
            return product;
        }).collect(Collectors.toList());

        for (Product prFV : productListForValidation) {

            Optional<Float> newPrice = priceValidatorUtils.getValidPriceByCssQuery(prFV.getUrlForValidating(),
                    prFV.getCssQueryForValidating());

            if (newPrice.isPresent()) {
                prFV.setPrice(newPrice.get());
                prFV.setValidationStatus(true);
                prFV.updateLastValidationDate();
            } else prFV.setValidationStatus(false);

        }


        productService.save(productListForExSave);


        productRestService.postProducts(productService.save(productListForValidation));


    }

    public List<Product> validateOne(Product product) throws ImpossibleEntitySaveUpdateException {

        Product syncedProduct;

        try {
            syncedProduct = dbRestSynchronizer.synchronizeOneDbProductWithRestApiModel(product);
        }catch (ImpossibleEntitySaveUpdateException e){
            return Collections.emptyList();
        }

        if(product.isDataForValidatingExist()){
            Optional<Float> newPrice = priceValidatorUtils.getValidPriceByCssQuery(syncedProduct.getUrlForValidating(),
                    syncedProduct.getCssQueryForValidating());

            if (newPrice.isPresent()) {
                syncedProduct.setPrice(newPrice.get());
                syncedProduct.setValidationStatus(true);
                syncedProduct.updateLastValidationDate();
            }else syncedProduct.setValidationStatus(false);

        }else syncedProduct.setValidationStatus(false);

        List<Product> prodListForVal = new ArrayList<>();

        prodListForVal.add(syncedProduct);


        return productRestService.postProducts(productService.save(prodListForVal));

    }


}
