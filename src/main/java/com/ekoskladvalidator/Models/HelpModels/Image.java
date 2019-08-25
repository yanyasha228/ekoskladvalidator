package com.ekoskladvalidator.Models.HelpModels;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@Data
@XmlRootElement(name = "image")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "image")
public class Image {
    public Image() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String url;

    @Column
    private String thumbnail_url;

}
