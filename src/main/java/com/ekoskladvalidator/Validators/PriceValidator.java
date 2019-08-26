package com.ekoskladvalidator.Validators;

import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.ParseUtils.CssQueryParser;
import com.ekoskladvalidator.RestServices.ProductRestService;
import com.ekoskladvalidator.Services.ProductService;
import com.ekoskladvalidator.SyncUtils.DbRestSynchronizer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class PriceValidator {

    @Autowired
    private ProductService productService;

    @Autowired
    private DbRestSynchronizer dbRestSynchronizer;

    @Autowired
    private CssQueryParser cssQueryParser;


    public void validatePrices() throws IOException {

        dbRestSynchronizer.synchronizeDbWithRestApiModels();

        List<Product> productListForValidation = productService.findAll().stream().
                filter(Product::isDataForValidatingExist).
                collect(Collectors.toList());

        for(Product prFV : productListForValidation){
            prFV.setPrice(Float.valueOf(cssQueryParser.getText(prFV.getUrlForValidating(),prFV.getCssQueryForValidating())));
        }
    }

}
