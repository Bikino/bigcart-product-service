package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdcutServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(long productId) {
        //TODO: else
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(long id, Product p) {
        Product product = productRepository.findById(id).get();
        if (product.equals(null)) {
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public Boolean deleteProduct(long productId) {
        if (getProduct(productId) == null)
            return false;
        productRepository.deleteById(productId);
        return true;
    }
}
