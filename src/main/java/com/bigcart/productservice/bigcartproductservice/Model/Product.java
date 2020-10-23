package com.bigcart.productservice.bigcartproductservice.Model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer categoryId;
    private String description;
    private String specs;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToMany
    @JoinTable
    private List<Review> reviews;

    @ManyToOne(cascade = CascadeType.ALL)
    private VendorProduct vendors;



    public Product() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
}
