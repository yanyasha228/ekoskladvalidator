package com.ekoskladvalidator.Models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    @Id
    private int id;

    @Column
    private String name;

}
