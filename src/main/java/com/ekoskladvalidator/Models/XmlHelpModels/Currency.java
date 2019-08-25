package com.ekoskladvalidator.Models.XmlHelpModels;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "currency")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {

    @XmlAttribute(name = "code")
    private com.ekoskladvalidator.Models.Enums.Currency code;

    @XmlAttribute(name = "rate")
    private double rate;
}
