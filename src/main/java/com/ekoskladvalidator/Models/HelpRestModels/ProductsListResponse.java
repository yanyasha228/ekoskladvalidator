package com.ekoskladvalidator.Models.HelpRestModels;

import com.ekoskladvalidator.Models.DTO.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductsListResponse implements Serializable {

    private List<ProductDto> products = new ArrayList<ProductDto>();

    private int group_id;

    private ErrorsResponse errors;

}
