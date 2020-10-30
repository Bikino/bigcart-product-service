package com.bigcart.productservice.bigcartproductservice.DTO;

import com.bigcart.productservice.bigcartproductservice.Model.Review;
import javassist.runtime.Desc;

import java.util.List;

public class VendorProductDTO {
    /* Given Requirements:
    LIST DTO (ID, Name, Picture URL  , PRICE , QTY, Desc, Specifications

            , Vendor name, List of reviews( name , review))



    Note: Specs String “comma separated values”

    Eg: Specs : (Dimentions,”12*13”,Ram ,12GB ,  )
    */

    private Long id;
    private String productName;
    private String imageUrl;
    private double price;
    private int quantity;
    private String description;
    private String Specifications;
    private String vendorName;
    private List<Review> reviewList;

    // take product and vendorproduct  then set vendor name
    public VendorProductDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecifications() {
        return Specifications;
    }

    public void setSpecifications(String specifications) {
        Specifications = specifications;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
