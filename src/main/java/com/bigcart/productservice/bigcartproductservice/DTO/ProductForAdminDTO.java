package com.bigcart.productservice.bigcartproductservice.DTO;

import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendorCKey;

import java.time.LocalDateTime;

public class ProductForAdminDTO {
//         Requested Fields
//
//        (Product Id, Product name, Category Id, Category name,
//
//        Vendor Id, Vendor name, Price, Date Posted)

    private ProductVendorCKey vendorproductId;
    private Long categoryId;
    private Long vendorId;
    private String vendorName;
    private String productName;
    private String categoryName;
    private double price;
    private int quantity;
    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;
    private LocalDateTime modificationDate;

    public ProductForAdminDTO() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductVendorCKey getVendorproductId() {
        return vendorproductId;
    }

    public void setVendorproductId(ProductVendorCKey vendorproductId) {
        this.vendorproductId = vendorproductId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
