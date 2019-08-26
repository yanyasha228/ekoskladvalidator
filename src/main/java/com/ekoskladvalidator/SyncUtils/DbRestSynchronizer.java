package com.ekoskladvalidator.SyncUtils;

import com.ekoskladvalidator.RestServices.GroupRestService;
import com.ekoskladvalidator.RestServices.ProductRestService;
import com.ekoskladvalidator.Services.GroupService;
import com.ekoskladvalidator.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    public void synchronizeDbWithRestApiModels(){
        synchronizeGroups();
        synchronizeProducts();
    }

    private void synchronizeGroups(){
        groupService.save(groupRestService.getAll());
    }
    private void synchronizeProducts(){
        productService.save(productRestService.getAll());
    }

}
