package com.bigcart.productservice.bigcartproductservice.Model;

import javax.persistence.Id;
import java.io.Serializable;

public class ProductVendorCKey implements Serializable {
    private Long vendorId;

    private Long productId;

    // default constructor
 public ProductVendorCKey(){}
    public ProductVendorCKey(Long productId, Long vendorId) {
        this.productId = productId;
        this.vendorId = vendorId;
    }

    // equals() and hashCode()
}
