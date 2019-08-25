package com.ekoskladvalidator.Services;

import com.ekoskladvalidator.Dao.ProductDao;
import com.ekoskladvalidator.Models.Group;
import com.ekoskladvalidator.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public List<Product> save(List<Product> productList) {
        return productDao.saveAll(productList);
    }

    @Override
    public Optional<Product> findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        return productDao.findProductByName(name);
    }

    @Override
    public List<Product> findProductsByNameIgnoreCaseContaining(String nonFullName) {

        return productDao.findProductsByNameIgnoreCaseContaining(nonFullName);
    }

    @Override
    public Page<Product> findAllWithPagination(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public Page<Product> findProductsByNameIgnoreCaseContainingWithPagination(String nonFullName,
                                                                              Pageable pageable) {
        return productDao.findProductsByNameIgnoreCaseContaining(nonFullName,
                pageable);
    }

    @Override
    public Page<Product> findProductsByDataForValidatingExistWithPagination(boolean dataForValidatingExist, Pageable pageable) {

        return productDao.findProductsByDataForValidatingExist(dataForValidatingExist,
                pageable);
    }

    @Override
    public Page<Product> findProductsByGroupWithPagination(Group group, Pageable pageable) {

        return productDao.findProductsByGroup(group,
                pageable);
    }

    @Override
    public Page<Product> findProductsByGroupAndDataForValidatingExistWithPagination(Group group, boolean dataForValidatingExist, Pageable pageable) {
        return productDao.findProductsByGroupAndDataForValidatingExist(group,
                dataForValidatingExist,
                pageable);
    }

    @Override
    public Page<Product> findProductsByNameIgnoreCaseContainingAndGroupWithPagination(String nonFullName,
                                                                                      Group group,
                                                                                      Pageable pageable) {
        return productDao.findProductsByNameIgnoreCaseContainingAndGroup(nonFullName,
                group,
                pageable);
    }

    @Override
    public Page<Product> findProductsByNameIgnoreCaseContainingAndDataForValidatingExistWithPagination(String nonFullName,
                                                                                                       boolean dataForValidatingExist,
                                                                                                       Pageable pageable) {

        return productDao.findProductsByNameIgnoreCaseContainingAndDataForValidatingExist(nonFullName,
                dataForValidatingExist,
                pageable);
    }

    @Override
    public Page<Product> findProductsByNameIgnoreCaseContainingAndGroupAndDataForValidatingExistWithPagination(String nonFullName, Group group, boolean dataForValidatingExist, Pageable pageable) {
        return productDao.findProductsByNameIgnoreCaseContainingAndGroupAndDataForValidatingExist(nonFullName,
                group,
                dataForValidatingExist,
                pageable);
    }

    @Override
    public Page<Product> findProductsWithPagination(String nonFullProductName,
                                                    Group group,
                                                    Boolean dataForValidatingExist,
                                                    Pageable pageable) {

        if (nonFullProductName.replaceAll(" ", "").isEmpty()) {

            if (dataForValidatingExist == null && group != null) {

                return findProductsByGroupWithPagination(group, pageable);
            }

            if (dataForValidatingExist != null && group == null) {

                return findProductsByDataForValidatingExistWithPagination(dataForValidatingExist, pageable);
            }
            if (dataForValidatingExist != null && group != null) {
                return findProductsByGroupAndDataForValidatingExistWithPagination(group,
                        dataForValidatingExist,
                        pageable);
            }
        } else {

            if (dataForValidatingExist == null && group != null) {

                return findProductsByNameIgnoreCaseContainingAndGroupWithPagination(nonFullProductName, group, pageable);
            }

            if (dataForValidatingExist != null && group == null) {

                return findProductsByNameIgnoreCaseContainingAndDataForValidatingExistWithPagination(nonFullProductName,
                        dataForValidatingExist,
                        pageable);
            }
            if (dataForValidatingExist != null && group != null) {
                return findProductsByNameIgnoreCaseContainingAndGroupAndDataForValidatingExistWithPagination(nonFullProductName,
                        group,
                        dataForValidatingExist,
                        pageable);
            }

            return findProductsByNameIgnoreCaseContainingWithPagination(nonFullProductName, pageable);
        }

        return findAllWithPagination(pageable);
    }

}
