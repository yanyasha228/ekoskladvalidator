package com.ekoskladvalidator.RestControllers;

import com.ekoskladvalidator.CustomExceptions.ImpossibleEntitySaveUpdateException;
import com.ekoskladvalidator.Models.Group;
import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.RestDao.ProductRestDao;
import com.ekoskladvalidator.RestServices.ProductRestService;
import com.ekoskladvalidator.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/groups")
public class GroupRestController {

    @Autowired
    private ProductRestService productRestService;

    @Autowired
    private GroupService groupService;

    @PostMapping("add")
    private void check(@RequestParam Integer id) throws ImpossibleEntitySaveUpdateException {

        Group group = new Group();
        group.setId(id);

        List<Product> productList = productRestService.getProductsByGroupId(id);

        if (id > 0) {
            if (!productRestService.getProductsByGroupId(id).isEmpty()){
                groupService.save(group);
            }
        }

    }

}
