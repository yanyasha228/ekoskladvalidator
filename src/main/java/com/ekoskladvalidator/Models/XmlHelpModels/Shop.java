package com.ekoskladvalidator.Models.XmlHelpModels;



import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "shop" , namespace = "https://www.w3.org/1999/xhtml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Shop {

    @XmlElement(name = "currency")
    private Currency currency;

    @XmlElement(name = "catalog")
    private Catalog catalog;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<Item> items;

}
