package com.ekoskladvalidator.Models.DTO;

import com.ekoskladvalidator.Models.Enums.Status;
import com.ekoskladvalidator.Models.Group;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {

    private int id;

    private String name;

    private float price;

    private Status status;

    private String currency;

    private String main_image;

    private Group group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
