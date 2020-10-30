
package com.bigcart.productservice.bigcartproductservice.Controller;

import com.bigcart.productservice.bigcartproductservice.DTO.FullProductDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.ListItmeDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.VendorProductDTO;
import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendor;
import com.bigcart.productservice.bigcartproductservice.Services.CategoryService;
import com.bigcart.productservice.bigcartproductservice.Services.ProductService;
import com.bigcart.productservice.bigcartproductservice.Services.ProductVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productvendors")
public class ProductVendorController {
    @Autowired
    ProductVendorService productVendorService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/")
    public ResponseEntity<List<ProductVendor>> getAllProductVendors() {

        List<ProductVendor> pvList = productVendorService.findAll();

        if (pvList == null) {
            return new ResponseEntity<List<ProductVendor>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<ProductVendor>>(pvList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{productId}/{vendorId}")
    public ResponseEntity getProductVendorById(@PathVariable Long productId, @PathVariable Long vendorId) {
        ProductVendor pv = productVendorService.findById(productId, vendorId);
        if (pv == null) {
            return new ResponseEntity("Product vendor doesn't exist.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(pv, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity addProduct(@RequestBody FullProductDTO fullProductDTO) {

        //create product
        Product product = new Product();
        product.setName(fullProductDTO.getName());
        product.setStatus("Pending");
        product.setImageUrl(fullProductDTO.getImageUrl());
        product.setSpecs(fullProductDTO.getSpecs());
        product.setDescription(fullProductDTO.getDescription());

        //set category
        product.setCategory(categoryService.getCategory(fullProductDTO.getCategoryId()));
        Product p = productService.addProduct(product);

        //create product vendor
        ProductVendor productVendor = new ProductVendor();
        productVendor.setProductId(p.getId());
        productVendor.setPrice(fullProductDTO.getPrice());
        productVendor.setQty(fullProductDTO.getQty());
        productVendor.setVendorId(fullProductDTO.getVendorId());

        productVendorService.save(productVendor);


        return new ResponseEntity("Product added for admin review.", new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/sellsameproduct")
    public ResponseEntity sellSameProduct(@RequestBody ProductVendor productVendor) {

        if(productVendor == null)
            return new ResponseEntity("Posted product vendor is null.", HttpStatus.BAD_REQUEST);
        if(productVendor.getProductId() == null)
            return new ResponseEntity("Posted product doesn't include product id.", HttpStatus.BAD_REQUEST);
        if(productVendor.getVendorId() == null)
            return new ResponseEntity("Posted product doesn't include vendor id.", HttpStatus.BAD_REQUEST);

        // verify vendor exists here

        Product p = productService.findById(productVendor.getProductId());
        if(p == null) {
            return new ResponseEntity("No such product exists.", HttpStatus.BAD_REQUEST);
        }

        ProductVendor pv = productVendorService.save(productVendor);

        if (pv == null) {
            return new ResponseEntity("Product vendor already exists.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(pv, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/")
    public ResponseEntity<ProductVendor> updateProductVendor(@RequestBody ProductVendor productVendor) {

        HttpHeaders headers = new HttpHeaders();

        if (productVendor == null) {
            return new ResponseEntity<ProductVendor>(HttpStatus.BAD_REQUEST);
        }
        productVendorService.save(productVendor);

        return new ResponseEntity<ProductVendor>(productVendor, headers, HttpStatus.CREATED);
    }










//    @PostMapping("/remove")
//    public void removeProducts(@RequestBody ListItmeDTO listDto) {
//        productVendorService.removeProductV(listDto);
//    }

    @GetMapping(value = "/test")
    public List<VendorProductDTO> findAllVendorProductsDTO() {
        List<VendorProductDTO> vendorProductDTOList = new ArrayList<VendorProductDTO>();

        // get vendor product list

        // get product list

        // call to get vendor name

        // merge and return


       // List <ProductVendor> productVendorList = productVendorService.getProductV();

//        RestTemplate restTemplate = new RestTemplate();
//        List<VendorProductDTO> list = restTemplate.getForObject("USERMANAGEMENT-SERVICE", ArrayList.class);
//        for (Ven)

        int i = 0;

        RestTemplate restTemplate = new RestTemplate();
        VendorProductDTO vendor = restTemplate.getForObject("usermanagement-service/vendor/1", VendorProductDTO.class);

        return vendorProductDTOList;
    }


}
