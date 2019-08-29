package com.ekoskladvalidator.SyncUtils;

import com.ekoskladvalidator.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.ekoskladvalidator.Models.Group;
import com.ekoskladvalidator.Models.HelpRestModels.SynchronizeDto;
import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.RestServices.GroupRestService;
import com.ekoskladvalidator.RestServices.ProductRestService;
import com.ekoskladvalidator.Services.GroupService;
import com.ekoskladvalidator.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DbRestSynchronizer {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRestService productRestService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRestService groupRestService;


    public SynchronizeDto synchronizeDbWithRestApiModels() {

        SynchronizeDto synchronizeDto = new SynchronizeDto();

        synchronizeDto.setGroups(synchronizeGroups());

        synchronizeDto.setProducts(synchronizeProducts());


        return synchronizeDto;
    }

    public Product synchronizeOneDbProductWithRestApiModel(Product productForSync) throws ImpossibleEntitySaveUpdateException {


        return productService.save(productRestService.getProductById(productForSync.getId()).orElse(null));

    }

    private List<Group> synchronizeGroups() {

        return groupService.save(groupRestService.getAll());

    }

    private List<Product> synchronizeProducts() {

        return productService.save(productRestService.getAll());

    }

    public void postAllProductsFromDb() {
        productRestService.postProducts(
                productService.findAll());
    }

}
