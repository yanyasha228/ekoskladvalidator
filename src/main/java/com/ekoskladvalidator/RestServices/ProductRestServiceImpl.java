package com.ekoskladvalidator.RestServices;

import com.ekoskladvalidator.Models.DTO.GroupDto;
import com.ekoskladvalidator.Models.DTO.ProductDto;
import com.ekoskladvalidator.Models.Group;
import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.ObjectMappers.ProductMapper;
import com.ekoskladvalidator.RestDao.GroupRestDao;
import com.ekoskladvalidator.RestDao.ProductRestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductRestServiceImpl implements ProductRestService {

    @Autowired
    private GroupRestDao groupRestDao;

    @Autowired
    private ProductRestDao productRestDao;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Optional<Product> getProductById(int id) {
        return Optional.of(productMapper.toEntity(productRestDao.getProductById(id).orElse(null)));
    }

    @Override
    public List<Product> getAll() {

        List<Product> productList = new ArrayList<>();
        for ( GroupDto gr: groupRestDao
                .getAllGroups()) {
            productList.addAll(productRestDao.getProductsByGroupId(gr.getId()).stream()
                    .map(productDto -> productMapper.toEntity(productDto)).collect(Collectors.toList()));
        }

        return productList;
    }

    @Override
    public List<Product> getProductsByGroupId(int id) {
        return productRestDao.
                getProductsByGroupId(id).stream().
                map(productDto -> productMapper.toEntity(productDto)).collect(Collectors.toList());
    }

    @Override
    public List<Product> postProducts(List<Product> productList) {

        return  productRestDao.postProducts(productList.stream().
                map(product -> productMapper.toDto(product))
                .collect(Collectors.toList())).stream().
                map(productDto -> productMapper.toEntity(productDto)).collect(Collectors.toList());
    }
}