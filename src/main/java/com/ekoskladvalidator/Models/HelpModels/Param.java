package com.ekoskladvalidator.Models.HelpModels;


import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "param")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "param")
public class Param {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @XmlAttribute(name = "name")
    private String name;

    @Column(name = "unit")
    @XmlAttribute(name = "unit")
    private String unit;

    @Column(name = "value")
    @XmlValue
    private String value;

}
