package com.ekoskladvalidator.ObjectMappers;

import com.ekoskladvalidator.Models.DTO.ProductDto;
import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.Services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductService productService;


    public Product toEntity(ProductDto productDto) {
        if(productDto==null) return null;
        Product product = mapper.map(productDto, Product.class);
        Product oldProduct =productService.findById(productDto.getId()).orElse(null);
        if(oldProduct!=null){
            product.setLastValidationDate(oldProduct.getLastValidationDate());
            product.setUrlForValidating(oldProduct.getUrlForValidating());
            product.setCssQueryForValidating(oldProduct.getCssQueryForValidating());
            product.setValidationStatus(oldProduct.isValidationStatus());
            product.setDataForValidatingExist(oldProduct.isDataForValidatingExist());
            product.setValidationStatus(oldProduct.isValidationStatus());
        }

        return product;
//        return Objects.isNull(productDto) ? null : mapper.map(productDto, Product.class);
    }


    public com.ekoskladvalidator.Models.DTO.ProductDto toDto(Product product) {
        return Objects.isNull(product) ? null : mapper.map(product, com.ekoskladvalidator.Models.DTO.ProductDto.class);
    }


}
