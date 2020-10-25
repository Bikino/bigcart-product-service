package com.bigcart.productservice.bigcartproductservice.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(ProductVendorCKey.class)
public class ProductVendor {
    @Id
    private Integer vendorId;
    @Id
    private Integer productId;
    private Date dateAdded;
    private Date dateModified;
    private Integer qty;
    private Float price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVendor vendorProduct = (ProductVendor) o;
        return vendorId.equals(vendorProduct.vendorId) &&
               productId.equals(vendorProduct.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, vendorId);
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public void decQty(Integer qty) {
        this.qty = this.qty-qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setVendorid(Integer id) {
        this.vendorId = id;
    }


    public Integer getVendorid() {
        return vendorId;
    }
}
