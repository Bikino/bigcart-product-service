package com.bigcart.productservice.bigcartproductservice.Repository;

import com.bigcart.productservice.bigcartproductservice.Model.ProductVendor;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendorCKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVendorRepository extends JpaRepository<ProductVendor, ProductVendorCKey> {
}
