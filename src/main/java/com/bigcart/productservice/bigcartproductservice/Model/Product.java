package com.bigcart.productservice.bigcartproductservice.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @JsonIgnore
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable = false,updatable = false)
    private Long categoryId;
    private String name;
    private String description;
    private String specs;

    public Product() {
    }

    //Relations
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany()
    @JsonIgnore
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private List<ProductVendor> ProductVendor = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long productId) {
        this.id = productId;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<com.bigcart.productservice.bigcartproductservice.Model.ProductVendor> getProductVendor() {
        return ProductVendor;
    }

    public void setProductVendor(List<com.bigcart.productservice.bigcartproductservice.Model.ProductVendor> productVendor) {
        ProductVendor = productVendor;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
