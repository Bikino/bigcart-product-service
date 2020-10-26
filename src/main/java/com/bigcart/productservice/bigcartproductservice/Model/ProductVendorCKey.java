package com.bigcart.productservice.bigcartproductservice.Model;

import javax.persistence.Id;
import java.io.Serializable;

public class ProductVendorCKey implements Serializable {
    private Long vendorId;

    private Long productId;

    // default constructor
 public ProductVendorCKey(){}
    public ProductVendorCKey(Long vendorId, Long productId) {
        this.vendorId = vendorId;
        this.productId = productId;
    }

    // equals() and hashCode()
}
