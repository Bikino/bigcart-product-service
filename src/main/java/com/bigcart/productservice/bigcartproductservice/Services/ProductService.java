package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);

    public Product findById(long productId) ;

    public List<Product> findAll();

    public Product update(Product product);

    public Boolean delete(long productId);

    public Product findByCategoryId(Long categoryId);
}
