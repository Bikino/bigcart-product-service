package com.bigcart.productservice.bigcartproductservice.Repository;
import java.util.List;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCategoryId(Long id);
}
