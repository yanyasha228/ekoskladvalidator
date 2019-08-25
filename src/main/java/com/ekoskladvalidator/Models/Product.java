package com.ekoskladvalidator.Models;


import com.ekoskladvalidator.Models.Enums.Currency;
import com.ekoskladvalidator.Models.Enums.Presence;
import com.ekoskladvalidator.Models.Enums.SellingType;
import com.ekoskladvalidator.Models.Enums.Status;
import com.ekoskladvalidator.Models.HelpModels.*;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String external_id;

    @Column
    private String name;

    @Column
    private String sku;

    @Column
    private String keywords;

    @Column
    private String description;

    @Column
    private SellingType selling_type;

    @Column
    private Presence presence;

    @Column
    private boolean presence_sure;

    @Column
    private Float price;

    @Column
    private Float minimum_order_quantity;

    @Column
    @OneToOne(orphanRemoval = true)
    private Discount discount;

    @Column
    private Currency currency;

    @Column
    private Group group;

    @Column
    private Category category;

    @Column
    private List<WholeSalePrice> prices;

    @Column
    private String main_page;

    @Column
    private List<Image> images;

    @Column
    private Status status;

}
