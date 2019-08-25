package com.ekoskladvalidator.HelpUtils;

import com.ekoskladvalidator.Models.DTO.ProductDto;
import com.ekoskladvalidator.Models.Group;
import com.ekoskladvalidator.Models.HelpRestModels.GroupsRestList;
import com.ekoskladvalidator.Models.HelpRestModels.ProductsRestList;
import com.ekoskladvalidator.Models.Product;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RestHelper {

    @Value("${rest.api.token}")
    private String apiToken;

    @Value("${rest.get.groups}")
    private String getGroupsListUrl;

    @Value("${rest.get.products.list.group_id}")
    private String getProductsByGroupIdUrl;

    @Value("${rest.post.products.edit}")
    private String postProductsEdit;

    @Autowired
    private RestTemplate restTemplate;


    public List<Group> getGroups() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", String.format("Bearer %s", apiToken));
        HttpEntity<GroupsRestList> entity = new HttpEntity<GroupsRestList>(headers);
        ResponseEntity<GroupsRestList> groupsRestListResponseEntity = restTemplate.exchange(getGroupsListUrl, HttpMethod.GET, entity, GroupsRestList.class);
        if (groupsRestListResponseEntity.getStatusCode() == HttpStatus.OK) {
            return groupsRestListResponseEntity.getBody().getGroups();

        }
        return new ArrayList<>();
    }

    public List<ProductDto> getProductsByGroupId(int groupId) {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Group> groupList = getGroups();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", String.format("Bearer %s", apiToken));
        HttpEntity<ProductsRestList> entity = new HttpEntity<ProductsRestList>(headers);

        ResponseEntity<ProductsRestList> productsRestListResponseEntity = restTemplate.exchange(String.format(getProductsByGroupIdUrl, String.valueOf(groupId)), HttpMethod.GET, entity, ProductsRestList.class);
        if (productsRestListResponseEntity.getStatusCode() == HttpStatus.OK) {
            productDtos.addAll(productsRestListResponseEntity.getBody().getProducts());
        }

        return new ArrayList<>();
    }

    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Group> groupList = getGroups();

        for (Group group : groupList) {
            productDtos.addAll(getProductsByGroupId(group.getId()));
        }

        return productDtos;
    }


    public String postProducts(List<ProductDto> productDtos){

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", String.format("Bearer %s", apiToken));

        HttpEntity<List<ProductDto>> entity = new HttpEntity<List<ProductDto>>(productDtos,headers);

        ResponseEntity<String> productsRestListResponseEntity = restTemplate.exchange(postProductsEdit , HttpMethod.POST, entity, String.class);

        return productsRestListResponseEntity.getBody();
    }


}
