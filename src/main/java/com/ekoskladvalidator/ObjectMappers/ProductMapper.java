package com.ekoskladvalidator.ObjectMappers;

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


    public Product toEntity(com.ekoskladvalidator.Models.DTO.ProductDto productDto) {
        if(productDto==null) return null;
        Product product = mapper.map(productDto, Product.class);
        Product oldProduct =productService.findById(productDto.getId()).orElse(null);
        if(oldProduct!=null){
            product.setLastValidationDate(oldProduct.getLastValidationDate());
            product.setUrlForValidating(oldProduct.getUrlForValidating());
            product.setCssQueryForValidating(oldProduct.getCssQueryForValidating());
            product.setDataForValidatingExist(oldProduct.isDataForValidatingExist());
        }

        return product;
//        return Objects.isNull(productDto) ? null : mapper.map(productDto, Product.class);
    }


    public com.ekoskladvalidator.Models.DTO.ProductDto toDto(Product product) {
        return Objects.isNull(product) ? null : mapper.map(product, com.ekoskladvalidator.Models.DTO.ProductDto.class);
    }


}
