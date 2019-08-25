package com.ekoskladvalidator.Models.DTO;

import com.ekoskladvalidator.Models.HelpModels.Discount;
import com.ekoskladvalidator.Models.Enums.Presence;
import com.ekoskladvalidator.Models.Enums.SellingType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDTO {


    private int id;


    private String external_id;


    private String name;


    private String sku;


    private String keywords;


    private String description;


    private SellingType selling_type;


    private Presence presence;


    private boolean presence_sure;

    private Float price;

    private Float minimum_order_quantity;

    private Discount discount;



}
