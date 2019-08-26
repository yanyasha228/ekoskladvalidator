package com.ekoskladvalidator.Models;


import com.ekoskladvalidator.Models.Enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    private int id;

    @Column
    private String name;

    @Column
    private Float price;

    @Column
    private String currency;

    @Column
    private Status status;

    @Column
    private String main_image;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private Group group;

    @Column(name = "last_validation_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastValidationDate;

    @Column(name = "url_for_validating")
    private String urlForValidating;

    @Column(name = "css_query_for_validating")
    private String cssQueryForValidating;

    @Column(name = "url_for_validating_exist")
    @Type(type = "true_false")
    private boolean dataForValidatingExist;


    @PreUpdate
    @PrePersist
    protected void prePersistUpdate() {

        updateLastValidationDate();

        modifyDataForValidationFlag();

    }


    protected void modifyDataForValidationFlag() {

        if (urlForValidating != null && cssQueryForValidating != null) {
            if (urlForValidating.replaceAll(" ", "").equalsIgnoreCase("") ||
                    cssQueryForValidating.replaceAll(" ", "").equalsIgnoreCase("")) {
                setDataForValidatingExist(false);
            } else setDataForValidatingExist(true);
        } else setDataForValidatingExist(false);


    }

    private void updateLastValidationDate(){
        lastValidationDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
