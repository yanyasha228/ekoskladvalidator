package com.ekoskladvalidator.Models.HelpRestModels;

import com.ekoskladvalidator.Models.DTO.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {

    private ProductDto product;

    private ErrorsResponse errors;
}
