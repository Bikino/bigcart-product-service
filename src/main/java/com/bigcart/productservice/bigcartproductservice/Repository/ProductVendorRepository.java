package com.bigcart.productservice.bigcartproductservice.Repository;

import com.bigcart.productservice.bigcartproductservice.Model.ProductVendor;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendorCKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVendorRepository extends JpaRepository<ProductVendor, ProductVendorCKey> {
    ProductVendor findByVendorId(Long id);
    List<ProductVendor> findAllByVendorId(Long id);
}
