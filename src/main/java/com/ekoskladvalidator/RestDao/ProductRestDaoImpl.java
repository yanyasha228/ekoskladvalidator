package com.ekoskladvalidator.RestDao;

import com.ekoskladvalidator.Models.DTO.ProductDto;
import com.ekoskladvalidator.Models.HelpRestModels.EditProductsResponse;
import com.ekoskladvalidator.Models.HelpRestModels.ProductResponse;
import com.ekoskladvalidator.Models.HelpRestModels.ProductsListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@Component
public class ProductRestDaoImpl implements ProductRestDao {

    @Value("${rest.prom.api.token}")
    private String apiToken;

    @Value("${rest.prom.api.get.products.list.group_id}")
    private String getProductsByGroupIdUri;

    @Value("${rest.prom.api.post.products.edit}")
    private String postProductsEditUri;

    @Value("${rest.prom.api.get.products.id}")
    private String getProductByIdUri;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Optional<ProductDto> getProductById(int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", String.format("Bearer %s", apiToken));
        HttpEntity<ProductResponse> entity = new HttpEntity<ProductResponse>(headers);

        ResponseEntity<ProductResponse> productResponseEntity = restTemplate.exchange(String.format(getProductByIdUri, String.valueOf(id)), HttpMethod.GET, entity, ProductResponse.class);

        if (productResponseEntity.getStatusCode() == HttpStatus.OK) {
            if (productResponseEntity.getBody() != null) {
                return Optional.of(productResponseEntity.getBody().getProduct());

            }
        }

        return Optional.empty();
    }

    @Override
    public List<ProductDto> getProductsByGroupId(int groupId) {
        List<ProductDto> productDtos = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", String.format("Bearer %s", apiToken));
        HttpEntity<ProductsListResponse> entity = new HttpEntity<ProductsListResponse>(headers);

        ResponseEntity<ProductsListResponse> productsListResponseEntity = restTemplate.exchange(String.format(getProductsByGroupIdUri, String.valueOf(groupId)), HttpMethod.GET, entity, ProductsListResponse.class);

        if (productsListResponseEntity.getStatusCode() == HttpStatus.OK) {
            if (productsListResponseEntity.getBody()!= null) {
                productDtos.addAll(productsListResponseEntity.getBody().getProducts());
                return productDtos;
            }
        }

        return Collections.emptyList();
    }


    @Override
    public List<ProductDto> postProducts(List<ProductDto> productDtos) {

        List<ProductDto> productDtoResponseList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", String.format("Bearer %s", apiToken));

        HttpEntity<List<com.ekoskladvalidator.Models.DTO.ProductDto>> entity = new HttpEntity<List<ProductDto>>(productDtos, headers);

        ResponseEntity<EditProductsResponse> productsEditResponseEntity = restTemplate.exchange(postProductsEditUri, HttpMethod.POST, entity, EditProductsResponse.class);

        if (productsEditResponseEntity.getStatusCode() == HttpStatus.OK) {
            if (productsEditResponseEntity.getBody() != null) {
                for(ProductDto productDtoIter : productDtos) {
                    for (Integer prodUpId : productsEditResponseEntity
                            .getBody().getProcessed_ids()
                    ) {
                        if (productDtoIter.getId() == prodUpId) productDtoResponseList.add(productDtoIter);
                    }
                }
                return productDtoResponseList;
            }
        }

        return Collections.emptyList();

    }


}
