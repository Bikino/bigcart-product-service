package com.bigcart.productservice.bigcartproductservice.Controller;

import com.bigcart.productservice.bigcartproductservice.DTO.*;
import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import com.bigcart.productservice.bigcartproductservice.Services.CategoryService;
import com.bigcart.productservice.bigcartproductservice.Services.ProductService;
import com.bigcart.productservice.bigcartproductservice.Services.VendorProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("vendorproduct")
public class VendorProductController {
    @Autowired
    VendorProductService vendorProductService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/")
    public ResponseEntity<List<VendorProduct>> getAllProductVendors() {

        List<VendorProduct> pvList = vendorProductService.findAll();

        if (pvList == null) {
            return new ResponseEntity<List<VendorProduct>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<VendorProduct>>(pvList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{vendorId}/{productId}")
    public ResponseEntity getProductVendorById(@PathVariable Long vendorId, @PathVariable Long productId) {
        VendorProduct pv = vendorProductService.findById(vendorId, productId);
        if (pv == null) {
            return new ResponseEntity("Vendor product doesn't exist.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(pv, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity addProductRequest(@RequestBody FullProductDTO fullProductDTO) {

        Product product = fullProductDTO.getProduct();
        productService.addProduct(product);
        Category category = fullProductDTO.getCategory();
        categoryService.save(category);
        VendorProduct vendorProduct = fullProductDTO.getVendorProduct();
        vendorProduct.setStatus("pending");
        vendorProductService.save(vendorProduct);
        return new ResponseEntity("Product added for admin review.", new HttpHeaders(), HttpStatus.CREATED);
//
//
//
//        //create product
//        Product product = new Product();
//        product.setName(fullProductDTO.getProductName());
//        product.setSpecifications(fullProductDTO.getSpecifications());
//        product.setDescription(fullProductDTO.getDescription());
//
//        //set category
//        product.setCategory(categoryService.findById(fullProductDTO.getCategoryId()));
//        Product p = productService.addProduct(product);
//
//        //create product vendor
//        VendorProduct vendorProduct = new VendorProduct();
//        vendorProduct.setProductId(p.getProductId());
//        vendorProduct.setPrice(fullProductDTO.getPrice());
//        vendorProduct.setQuantity(fullProductDTO.getQuantity());
//        vendorProduct.setStatus(fullProductDTO.getStatus());
//        vendorProduct.setImageUrl(fullProductDTO.getImageUrl());
////      vendorProduct.setReviews(new ArrayList<>());
//        vendorProduct.setVendorId(fullProductDTO.getVendorId());
//        vendorProduct.setStatus("Pending");
//        vendorProduct.setImageUrl(fullProductDTO.getImageUrl());
//        vendorProduct.setRequestDate(LocalDateTime.now());
//
//        vendorProductService.save(vendorProduct);

    }

    @PostMapping(value = "/sellsameproduct")
    public ResponseEntity sellSameProduct(@RequestBody VendorProduct vendorProduct) {

        if (vendorProduct == null)
            return new ResponseEntity("Posted product vendor is null.", HttpStatus.BAD_REQUEST);
        if (vendorProduct.getProductId() == null)
            return new ResponseEntity("Posted product doesn't include product id.", HttpStatus.BAD_REQUEST);
        if (vendorProduct.getVendorId() == null)
            return new ResponseEntity("Posted product doesn't include vendor id.", HttpStatus.BAD_REQUEST);

        // verify vendor exists here

        Product p = productService.findById(vendorProduct.getProductId());
        if (p == null) {
            return new ResponseEntity("No such product exists.", HttpStatus.BAD_REQUEST);
        }

        VendorProduct pv = vendorProductService.save(vendorProduct);

        if (pv == null) {
            return new ResponseEntity("Product vendor already exists.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(pv, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/")
    public ResponseEntity<VendorProduct> updateProductVendor(@RequestBody VendorProduct vendorProduct) {

        HttpHeaders headers = new HttpHeaders();

        if (vendorProduct == null) {
            return new ResponseEntity<VendorProduct>(HttpStatus.BAD_REQUEST);
        }
        vendorProductService.save(vendorProduct);
        return new ResponseEntity<VendorProduct>(vendorProduct, headers, HttpStatus.CREATED);
    }

//    @PostMapping("/remove")
//    public void removeProducts(@RequestBody ListItmeDTO listDto) {
//        productVendorService.removeProductV(listDto);
//    }

    @GetMapping(value = "/findAllVendorProductsDTO")
    public ResponseEntity findAllVendorProductsDTOforVendor() {
        List<ProductVendorDTO> productVendorDTOList = new ArrayList<ProductVendorDTO>();

        List<VendorProduct> vendorProductList = vendorProductService.findAll();

        List<Product> productList = productService.findAll();

        RestTemplate restTemplate = new RestTemplate();

        for (VendorProduct vendorProduct : vendorProductList) {
            ProductVendorDTO productVendorDTO = new ProductVendorDTO();
            Product product = productService.findById(vendorProduct.getProductId());
            productVendorDTO.setProductId(vendorProduct.getProductId());
            productVendorDTO.setVendorId(vendorProduct.getVendorId());
            productVendorDTO.setName(product.getName());
            productVendorDTO.setDescription(product.getDescription());
            productVendorDTO.setSpecifications(product.getSpecifications());
            productVendorDTO.setImageUrl(vendorProduct.getImageUrl());
            productVendorDTO.setPrice(vendorProduct.getPrice());
            productVendorDTO.setQuantity(vendorProduct.getQuantity());
            productVendorDTO.setRequestDate(vendorProduct.getRequestDate());
            productVendorDTO.setApprovalDate(vendorProduct.getApprovalDate());
            productVendorDTO.setModificationDate(vendorProduct.getModificationDate());

            String vendorId = vendorProduct.getVendorId().toString();
            VendorNameDTO v = null;

            try {
                v = restTemplate.getForObject("http://localhost:9988/vendor/" + vendorId, VendorNameDTO.class);
            }
            catch (Exception e) {
                productVendorDTO.setVendorName("Unknown");
                productVendorDTOList.add(productVendorDTO);
                continue;
            }
            productVendorDTO.setVendorName(v.getCompanyName());
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

    @GetMapping(value = "/findProductForAdminDTO")
    public ResponseEntity findProductForAdminDTO(
            @RequestParam(required = false) String categoryId,
            String vendorProductId, String vendorId, String status) {

        if (status == null) {
            return new ResponseEntity(
                    "Product status is required."
                    , new HttpHeaders(), HttpStatus.OK);
        }

        if (!(status.equals("pending")) && !(status.equals("approved"))
                && !(status.equals("deleted")) && !(status.equals("declined"))) {
            return new ResponseEntity(
                    "Invalid product status."
                    , new HttpHeaders(), HttpStatus.OK);
        }

        if (vendorProductId == null) {
            if (categoryId == null) {
                if (vendorId == null) {
                    return new ResponseEntity(
                            vendorProductService.productToProductDTOList(
                                    vendorProductService.findAll(),
                                    status), new HttpHeaders(), HttpStatus.OK);
                } else {
                    return new ResponseEntity(vendorProductService.
                            productToProductDTOList(
                                    vendorProductService.findAllByVendorId(
                                            Long.parseLong(vendorId)), status),
                            new HttpHeaders(), HttpStatus.OK);
                }
            } else if (vendorId != null) {
                List<VendorProduct> pvList = vendorProductService.findAllByVendorId(
                        Long.parseLong(vendorId));
                List<VendorProduct> filteredList = new ArrayList<>();
                for (VendorProduct pv : pvList) {
                    Product p = productService.findById(pv.getProductId());
                    Category c = p.getCategory();
                    String cid = c.getCategoryId().toString();
                    if (cid.equals(categoryId)) {
                        filteredList.add(pv);
                    }
                }
                return new ResponseEntity(vendorProductService.productToProductDTOList(
                        filteredList, status), new HttpHeaders(), HttpStatus.OK);
            } else {
                List<VendorProduct> pvList = vendorProductService.findAll();
                List<VendorProduct> filteredList = new ArrayList<>();
                for (VendorProduct pv : pvList) {
                    Product p = productService.findById(pv.getProductId());
                    Category c = p.getCategory();
                    String cid = c.getCategoryId().toString();
                    if (cid.equals(categoryId)) {
                        filteredList.add(pv);
                    }
                }
                return new ResponseEntity(vendorProductService.productToProductDTOList(
                        filteredList, status), new HttpHeaders(), HttpStatus.OK);
            }
        } else if ((categoryId == null && vendorId == null)) {
            List<ProductForAdminDTO> list = new ArrayList<>();
            String[] s = vendorProductId.split("-");
            ProductForAdminDTO productForAdminDTO =
                    vendorProductService.productToProductDTO(
                            vendorProductService.findById(
                                    Long.parseLong(s[0]), Long.parseLong(s[1])), status);
            list.add(productForAdminDTO);
            return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
        } else if (categoryId == null) {
            List<ProductForAdminDTO> list = new ArrayList<>();
            String[] s = vendorProductId.split("-");
            VendorProduct pv = vendorProductService.findById(
                    Long.parseLong(s[0]), Long.parseLong(s[1]));
            if (pv.getVendorId().toString().equals(vendorId)) {
                list.add(vendorProductService.productToProductDTO(pv, status));
            }
            return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
        } else {
            List<ProductForAdminDTO> filteredList = new ArrayList<>();
            String[] s = vendorProductId.split("-");
            VendorProduct pv = vendorProductService.findById(
                    Long.parseLong(s[0]), Long.parseLong(s[1]));
            if (pv == null) {
                return new ResponseEntity(filteredList,
                        new HttpHeaders(), HttpStatus.OK);
            }

            Product p = productService.findById(pv.getProductId());
            Category c = p.getCategory();
            String cid = c.getCategoryId().toString();
            if (cid.equals(categoryId)) {
                filteredList.add(vendorProductService.productToProductDTO(pv, status));
            }
            return new ResponseEntity(filteredList, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/approveProduct")
    public ResponseEntity approveProduct(@RequestBody List<ApproveProductDTO> approveProductDTOList) {

        for (ApproveProductDTO approveProductDTO : approveProductDTOList) {
            String[] s = approveProductDTO.getVendorProductId().split("-");
            VendorProduct vendorProduct = null;
            try {
                vendorProduct = vendorProductService.findById(Long.parseLong(s[0]), Long.parseLong(s[1]));
            } catch (Exception e) {
                return new ResponseEntity("Improper product id", new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
            if (approveProductDTO.getApprovalCode() == 1) {
                vendorProduct.setStatus("approved");
            } else if (approveProductDTO.getApprovalCode() == 0) {
                vendorProduct.setStatus("declined");
            } else {
                return new ResponseEntity("Improper approval code.", new HttpHeaders(), HttpStatus.OK);
            }

            vendorProductService.save(vendorProduct);
        }
        return new ResponseEntity("ok", new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/findFullProductById/{id}")
    public ResponseEntity findFullProductById(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return new ResponseEntity("Improper vendorProduct id.", new HttpHeaders(), HttpStatus.OK);
        }

        String[] s = id.split("-");
        String vendorId = s[0];
        String productId = s[1];

        VendorProduct vendorProduct = vendorProductService.findById(Long.parseLong(vendorId), Long.parseLong(productId));
        if (vendorProduct == null) {
            return new ResponseEntity("No such product...", new HttpHeaders(), HttpStatus.OK);
        }
        Product product = productService.findById(vendorProduct.getProductId());
        Category category = product.getCategory();
        return new ResponseEntity(new FullProductDTO(category, product, vendorProduct), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody String id) {
        String[] s = id.split("-");
        String vendorId = s[0];
        String productId = s[1];
        VendorProduct vendorProduct = null;
        try {
            vendorProduct = vendorProductService.findById(Long.parseLong(vendorId), Long.parseLong(productId));
        } catch (Exception e) {
            return new ResponseEntity("Bad vendorProduct ID format is inputted.", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        if (vendorProduct == null) {
            return new ResponseEntity("No such vendorProduct exists.", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        if (vendorProduct.getStatus().equals("deleted")) {
            return new ResponseEntity("This vendorProduct is already deleted before.", new HttpHeaders(), HttpStatus.OK);

        }
        vendorProduct.setStatus("deleted");
        vendorProductService.save(vendorProduct);
        return new ResponseEntity("Given vendorProduct is deleted.", new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllFullProducts")
    public ResponseEntity findAllFullProducts() {
        List<FullProductDTO> fullProductDTOList = new ArrayList<>();
        for (VendorProduct vendorProduct : vendorProductService.findAll()) {
            Product product = productService.findById(vendorProduct.getProductId());
            Category category = product.getCategory();
            fullProductDTOList.add(new FullProductDTO(category, product, vendorProduct));
        }
        return new ResponseEntity(fullProductDTOList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/test")
    public ResponseEntity test() {
        int i =5;
        RestTemplate restTemplate = new RestTemplate();
        VendorNameDTO v = restTemplate.getForObject("http://localhost:9988/vendor/1", VendorNameDTO.class);

        return new ResponseEntity("", new HttpHeaders(), HttpStatus.OK);
    }

}






//    @GetMapping(value = "/getAllProductsDTOAdmin")
//    public ResponseEntity getAllProductsDTOAdmin(@RequestBody List<ApproveProductDTO> approveProductDTOList) {
//
//        return new ResponseEntity(productVendorService.
//                productToProductDTOList(productVendorService.findAll()),
//                new HttpHeaders(), HttpStatus.OK);
//    }
