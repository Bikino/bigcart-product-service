package com.bigcart.productservice.bigcartproductservice.Model;

import javax.persistence.*;

@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String name;

    public Category() {

    }

    public Integer getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Integer id) {
        this.categoryId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
