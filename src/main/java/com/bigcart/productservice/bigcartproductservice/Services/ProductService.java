package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);

    public Product getProduct(long productId) ;

    public List<Product> getProducts();

    public Product editProduct(long productID, Product edit_product);

    public Product deleteProduct(long productId);
}
