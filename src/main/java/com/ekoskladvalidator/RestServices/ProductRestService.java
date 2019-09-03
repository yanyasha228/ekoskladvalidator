package com.ekoskladvalidator.RestServices;


import com.ekoskladvalidator.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRestService {

    Optional<Product> getProductById(int id);

    List<Product> getAll() throws InterruptedException;

    List<Product> getProductsByGroupId(int id);

    List<Product> postProducts(List<Product> productList);


}
