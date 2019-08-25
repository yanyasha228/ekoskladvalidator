package com.ekoskladvalidator.Models.XmlHelpModels;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {

    @XmlAttribute(name = "id")
    private long id;

    @XmlAttribute(name = "parentId")
    private long parentId;

    @XmlAttribute(name = "portal_id")
    private long portalId;

    @XmlAttribute(name = "portal_url")
    private String portalUrl;

    @XmlValue
    private String categoryName;

}
