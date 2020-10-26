package com.bigcart.productservice.bigcartproductservice.Controller;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        HttpHeaders headers = new HttpHeaders();

        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        }
        productService.addProduct(product);

       return new ResponseEntity<Product>(product, headers, HttpStatus.CREATED);

    }


    @GetMapping(value = "/")
    public ResponseEntity<List<Product>> getProducts() {

        HttpHeaders headers = new HttpHeaders();

        List<Product> products = productService.getProducts();
        if (products == null) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);

    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId) {

        Product product = productService.getProduct(productId);

        if (product == null) {

            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> editProduct(@RequestBody Product product) {

        HttpHeaders headers = new HttpHeaders();
        Product product_toEdit = productService.getProduct(product.getProductId());

        if (product_toEdit == null) {

            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        Product updatedProduct = productService.editProduct(product.getProductId(), product);

        return new ResponseEntity<Product>(updatedProduct, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}")
    public  ResponseEntity deleteProduct(@PathVariable long productId) {

        return new ResponseEntity( productService.deleteProduct(productId)? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
