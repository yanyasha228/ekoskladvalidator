package com.ekoskladvalidator.Models.HelpModels;


import com.ekoskladvalidator.Models.Enums.Currency;
import com.ekoskladvalidator.Models.Enums.SellingType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "item")
public class Item {

    public Item() {
        creationDate = LocalDate.now();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute(name = "id")
    private long id;

    @Column(name = "selling_type")
    @Enumerated(EnumType.STRING)
    @XmlAttribute(name = "selling_type")
    @NotNull
    private SellingType sellingType = SellingType.retail;

    @Column(name = "name")
    @XmlElement(name = "name")
    @NotNull
    private String name;

    private String queryName;

    @Column(name = "portal_category_id")
    @XmlElement(name = "portal_category_id")
    private long portalCategoryId;

    @Column(name = "portal_category_url")
    @XmlElement(name = "portal_category_url")
    private String portalCategoryUrl;

    @Column(name = "price")
    @XmlElement(name = "price")
    private double price;

    private String queryPrice;

    @Column(name = "price_usd")
    @XmlElement(name = "priceusd")
    private double priceUsd;

    @Column(name = "price_uah")
    @XmlElement(name = "priceuah")
    private double priceUah;

    @Column(name = "images")
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @XmlElement(name = "image")
    private List<Image> images;

    private String queryImages;

    @Column(name = "vendor")
    @XmlElement(name = "vendor")
    private String vendor;

    private String queryVendor;


    @Column(name = "bar_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement(name = "barcode")
    private String barCode;

    @Column(name = "country")
    @XmlElement(name = "country")
    private String country;

    private String queryCountry;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Column(name = "params")
    @XmlElement(name = "param")
    private List<Param> params = new ArrayList<>();

    @Column(name = "description", length = 20000)
    @XmlElement(name = "description")
    private String description;

    @Column(name = "available")
    @XmlElement(name = "available")
    private boolean available;


    /////Need to spec generate with "," separator
    @Column(name = "keywords")
    @XmlElement(name = "keywords")
    private String keywords;

    @Column(name = "product_url_from_external_resource")
    private String productUrlFromExternalResource;

    @Column(name = "quantity")
    @Min(0)
    private int quantity;

    @Column(name = "moneyCurrency")
    @Enumerated(EnumType.STRING)
    private Currency currency;



    @Column(name = "creation_date")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(name = "last_updating_date")
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate lastUpdatingDate;



    //    @ManyToMany(mappedBy = "products")
////    private List<Order> ordersList;
//    @PreUpdate
//    @PrePersist
//    protected void modifyLastUpdatingDate() {
//        if (quantity > 0) {
//            available = true;
//        } else available = false;
//        lastUpdatingDate = new Date();
//    }


}
