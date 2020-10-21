package com.ekoskladvalidator.RestServices;

import com.ekoskladvalidator.Models.DTO.ProductDto;
import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.ObjectMappers.ProductMapper;
import com.ekoskladvalidator.RestDao.GroupRestDao;
import com.ekoskladvalidator.RestDao.ProductRestDao;
import com.ekoskladvalidator.Services.GroupService;
import com.ekoskladvalidator.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductRestServiceImpl implements ProductRestService {

    @Autowired
    private GroupRestDao groupRestDao;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ProductRestDao productRestDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Optional<Product> getProductById(int id) {
        Product product = productMapper.toEntity(productRestDao.getProductById(id).orElse(null));
        return Optional.of(product);
    }

    @Override
    public List<Product> getAll() throws InterruptedException {

        List<Product> productList = new ArrayList<>();

        for ( Product pr: productService.findAll()) {

            Optional<ProductDto> prodToAdd = productRestDao.getProductById(pr.getId());

            prodToAdd.ifPresent(productDto -> productList.add(productMapper.toEntity(productDto)));

            Thread.sleep(150);

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
    public List<Product> postProducts(List<Product> productList) throws InterruptedException {

        List<Product> productListForRet = new ArrayList<>();

        for (Product pr: productList) {
            List<Product> productsForDescSend = new ArrayList<>();
            productsForDescSend.add(pr);

            productListForRet.addAll(productRestDao.postProducts(productsForDescSend.stream()
                    .map(product -> productMapper.toDto(product))
                    .collect(Collectors.toList())).stream().
                    map(productDto -> productMapper.toEntity(productDto)).collect(Collectors.toList()));

            Thread.sleep(150);

        }

        return  productListForRet;
    }
}
