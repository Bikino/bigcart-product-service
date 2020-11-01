package com.bigcart.productservice.bigcartproductservice.Controller;

import com.bigcart.productservice.bigcartproductservice.DTO.FullProductDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.ProductForAdminDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.ProductVendorDTO;
import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendor;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendorCKey;
import com.bigcart.productservice.bigcartproductservice.Services.CategoryService;
import com.bigcart.productservice.bigcartproductservice.Services.ProductService;
import com.bigcart.productservice.bigcartproductservice.Services.ProductVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

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
    public ResponseEntity addProductRequest(@RequestBody FullProductDTO fullProductDTO) {

        //create product
        Product product = new Product();
        product.setName(fullProductDTO.getName());
        product.setSpecifications(fullProductDTO.getSpecs());
        product.setDescription(fullProductDTO.getDescription());

        //set category
        product.setCategory(categoryService.findById(fullProductDTO.getCategoryId()));
        Product p = productService.addProduct(product);

        //create product vendor
        ProductVendor productVendor = new ProductVendor();
        productVendor.setProductId(p.getId());
        productVendor.setPrice(fullProductDTO.getPrice());
        productVendor.setQuantity(fullProductDTO.getQty());
        productVendor.setStatus(fullProductDTO.getStatus());
        productVendor.setImageUrl(fullProductDTO.getImageUrl());
        productVendor.setReviews(new ArrayList<>());
        productVendor.setVendorId(fullProductDTO.getVendorId());
        productVendor.setStatus("Pending");
        productVendor.setImageUrl(fullProductDTO.getImageUrl());
        productVendor.setRequestDate(LocalDateTime.now());

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

    @GetMapping(value = "/findAllVendorProductsDTO")
    public ResponseEntity findAllVendorProductsDTOforVendor() {
        List<ProductVendorDTO> productVendorDTOList = new ArrayList<ProductVendorDTO>();

        List<ProductVendor> productVendorList = productVendorService.findAll();

        List<Product> productList = productService.findAll();

        for(ProductVendor productVendor : productVendorList) {
            ProductVendorDTO productVendorDTO = new ProductVendorDTO();
            Product product = productService.findById(productVendor.getProductId());
            productVendorDTO.setProductId(productVendor.getProductId());
            productVendorDTO.setVendorId(productVendor.getVendorId());
            productVendorDTO.setName(product.getName());
            productVendorDTO.setDescription(product.getDescription());
            productVendorDTO.setSpecifications(product.getSpecifications());
            productVendorDTO.setImageUrl(productVendor.getImageUrl());
            productVendorDTO.setPrice(productVendor.getPrice());
            productVendorDTO.setQuantity(productVendor.getQuantity());
            productVendorDTO.setRequestDate(productVendor.getRequestDate());
            productVendorDTO.setApprovalDate(productVendor.getApprovalDate());
            productVendorDTO.setModificationDate(productVendor.getModificationDate());
            // Pending to get requested function
            productVendorDTO.setVendorName("Microsoft");
            productVendorDTOList.add(productVendorDTO);
        }


        // call to get vendor name

        // merge and return


       // List <ProductVendor> productVendorList = productVendorService.getProductV();

//        RestTemplate restTemplate = new RestTemplate();
//        List<VendorProductDTO> list = restTemplate.getForObject("USERMANAGEMENT-SERVICE", ArrayList.class);
//        for (Ven)

//        int i = 0;
//
//        RestTemplate restTemplate = new RestTemplate();
//        ProductVendorDTO vendor = restTemplate.getForObject("usermanagement-service/vendor/1", ProductVendorDTO.class);

        return new ResponseEntity(productVendorDTOList, new HttpHeaders(), HttpStatus.OK);
    }

//    @GetMapping(value = "/findAllVendorProductsDTOforAdmin")
//    public ResponseEntity findAllVendorProductsDTOforAdmin() {
//        List<ProductForAdminDTO> productForAdminDTOList = new ArrayList<>();
//        for(ProductVendor productVendor : productVendorService.findAll()) {
//            Product product = productService.findById(productVendor.getProductId());
//            Category category = categoryService.findById(product.getCategoryId());
//            ProductForAdminDTO productForAdminDTO = new ProductForAdminDTO();
//            productForAdminDTO.setRequestDate(productVendor.getRequestDate());
//            productForAdminDTO.setApprovalDate(productVendor.getApprovalDate());
//            productForAdminDTO.setModificationDate(productVendor.getModificationDate());
//            productForAdminDTO.setCategoryId(category.getCategoryId());
//            productForAdminDTO.setCategoryName(category.getName());
//            productForAdminDTO.setPrice(productVendor.getPrice());
//            productForAdminDTO.setQuantity(productVendor.getQuantity());
//            productForAdminDTO.setVendorId(productVendor.getVendorId());
//            //Should be changed to make it dynamic vendor name.
//            productForAdminDTO.setVendorName("Microsoft");
//            productForAdminDTO.setProductName(product.getName());
//            productForAdminDTO.setVendorproductId(new ProductVendorCKey(productVendor.getProductId(), productVendor.getVendorId()));
//            productForAdminDTOList.add(productForAdminDTO);
//        }
//        return new ResponseEntity(productForAdminDTOList, new HttpHeaders(), HttpStatus.OK);
//    }

}
