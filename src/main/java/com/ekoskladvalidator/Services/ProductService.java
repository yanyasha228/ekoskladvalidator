package com.ekoskladvalidator.Services;


import com.ekoskladvalidator.Models.Group;
import com.ekoskladvalidator.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    Product save(Product product);

    List<Product> save(List<Product> productList);

    List<Product> findAll();

    Optional<Product> findById(int id);

    Optional<Product> findProductByName(String name);

    List<Product> findProductsByNameIgnoreCaseContaining(String nonFullName);

    Page<Product> findAllWithPagination(Pageable pageable);

    Page<Product> findProductsByNameIgnoreCaseContainingWithPagination(String nonFullName,
                                                                       Pageable pageable);

    Page<Product> findProductsByDataForValidatingExistWithPagination(boolean dataForValidatingExist,
                                                                     Pageable pageable);

    Page<Product> findProductsByGroupWithPagination(Group group,
                                                    Pageable pageable);

    Page<Product> findProductsByGroupAndDataForValidatingExistWithPagination(Group group,
                                                                             boolean dataForValidatingExist,
                                                                             Pageable pageable);

    Page<Product> findProductsByNameIgnoreCaseContainingAndGroupWithPagination(String nonFullName,
                                                                               Group group,
                                                                               Pageable pageable);

    Page<Product> findProductsByNameIgnoreCaseContainingAndDataForValidatingExistWithPagination(String nonFullName,
                                                                                                boolean dataForValidatingExist,
                                                                                                Pageable pageable);

    Page<Product> findProductsByNameIgnoreCaseContainingAndGroupAndDataForValidatingExistWithPagination(String nonFullName,
                                                                                                        Group group,
                                                                                                        boolean dataForValidatingExist,
                                                                                                        Pageable pageable);

    public Page<Product> findProductsWithPagination(String nonFullProductName,
                                                    Group group,
                                                    Boolean dataForValidatingExist,
                                                    Pageable pageable);


}
