package com.ekoskladvalidator.RestDao;

import com.ekoskladvalidator.Models.DTO.ProductDto;
import com.ekoskladvalidator.Models.HelpRestModels.EditProductsResponse;

import java.util.List;
import java.util.Optional;

public interface ProductRestDao {

    Optional<ProductDto> getProductById(int id);

    List<ProductDto> getProductsByGroupId(int id);

    List<ProductDto> postProducts(List<ProductDto> productDtos);

}
