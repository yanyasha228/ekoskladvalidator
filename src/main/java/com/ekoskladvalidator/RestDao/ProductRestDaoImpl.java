package com.ekoskladvalidator.RestDao;

import com.ekoskladvalidator.Models.DTO.ProductDto;
import com.ekoskladvalidator.Models.HelpRestModels.EditProductsResponse;
import com.ekoskladvalidator.Models.HelpRestModels.ProductResponse;
import com.ekoskladvalidator.Models.HelpRestModels.ProductsListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class ProductRestDaoImpl implements ProductRestDao {

    private static Logger logger = LoggerFactory.getLogger(ProductRestDaoImpl.class);

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

        logger.error("Executing entity with id: " + id);
        ResponseEntity<ProductResponse> productResponseEntity = restTemplate.exchange(String.format(getProductByIdUri, String.valueOf(id)), HttpMethod.GET, entity, ProductResponse.class);

        if (productResponseEntity.getStatusCode() == HttpStatus.OK) {
            if (productResponseEntity.getBody() != null) {
                logger.error("Executing final entity with id: " + productResponseEntity.getBody().getProduct().getId());
                return Optional.of(productResponseEntity.getBody().getProduct());

            } else {
                logger.error("LOOOOOOSEEEEE BODY IS NULL");
            }
        } else {
            logger.error("LOOOOOOSEEEEE STATUS CODE : " + productResponseEntity.getStatusCode());
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
            if (productsListResponseEntity.getBody() != null) {
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

        HttpEntity<List<ProductDto>> entity = new HttpEntity<List<ProductDto>>(productDtos, headers);

        logger.error("Posting entity with id: " + productDtos.get(0).getId());
        ResponseEntity<EditProductsResponse> productsEditResponseEntity = restTemplate.exchange(postProductsEditUri, HttpMethod.POST, entity, EditProductsResponse.class);

        if (productsEditResponseEntity.getStatusCode() == HttpStatus.OK) {
            if (productsEditResponseEntity.getBody() != null) {
                for (ProductDto productDtoIter : productDtos) {
                    for (Integer prodUpId : productsEditResponseEntity
                            .getBody().getProcessed_ids()
                    ) {
                        if (productDtoIter.getId() == prodUpId) {
                            productDtoResponseList.add(productDtoIter);
                            logger.error("Posting final WON with id: " + productDtoIter.getId());
                        } else {
                            logger.error("Posting final LOSE with id: " + productDtoIter.getId());
                        }

                    }
                }
                return productDtoResponseList;
            } else {
                logger.error("LOOOOOOSEEEEE BODY IS NULL");
            }

        } else {
            logger.error("LOOOOOOSEEEEE STATUS CODE : " + productsEditResponseEntity.getStatusCode());
        }
        return Collections.emptyList();

    }


}
