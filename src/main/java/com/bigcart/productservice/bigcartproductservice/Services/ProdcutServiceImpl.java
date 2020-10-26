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
    public Product editProduct(long productID, Product edit_product) {
        Product product = productRepository.findById(productID)
                .get();
        if (product.equals(null))
        return null;

        product.setName(edit_product.getName());
        product.setSpecs(edit_product.getSpecs());
        product.setDescription(edit_product.getDescription());
        product.setApproved(edit_product.getApproved());


        return productRepository.save(product);


    }

    @Override
    public Boolean deleteProduct(long productId) {
        if (getProduct(productId)==null)
        return false;
        productRepository.deleteById(productId);
        return true ;
    }
}
