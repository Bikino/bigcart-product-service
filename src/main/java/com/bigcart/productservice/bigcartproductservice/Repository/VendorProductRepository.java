package com.bigcart.productservice.bigcartproductservice.Repository;

import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorProductRepository extends JpaRepository<VendorProduct,Integer> {
}
