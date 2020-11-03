package com.bigcart.productservice.bigcartproductservice.Controller;

import com.bigcart.productservice.bigcartproductservice.DTO.ProductVendorDTO;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.ProductImage;
import com.bigcart.productservice.bigcartproductservice.Services.ProductImageService;
import com.bigcart.productservice.bigcartproductservice.Services.ProductService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    public static String uploadDirectory = System.getProperty("user.dir") + "/bigcart-product-service/src/main/resources/uploads";

    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @PostMapping(value = "/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        HttpHeaders headers = new HttpHeaders();

        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        }
        productService.addProduct(product).getProductId();

        return new ResponseEntity<Product>(product, headers, HttpStatus.CREATED);
    }



    @PutMapping(value = "/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

        Product serviceResult = productService.update(product);

        if ( serviceResult == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Product>(serviceResult, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Product>> getProducts() {

        HttpHeaders headers = new HttpHeaders();

        List<Product> products = productService.findAll();
        if (products == null) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);

    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId) {

        Product product = productService.findById(productId);

        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity deleteProduct(@PathVariable long productId) {

        return new ResponseEntity(productService.delete(productId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // This way is less efficient. Remove later on.
//    @PostMapping(value = "upload")
//    public String uploadImage(@RequestParam("file") MultipartFile file) {
//        Path p = Paths.get(uploadDirectory, file.getOriginalFilename());
//        try {
//            Files.write(p, file.getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "OK";
//    }

    @PostMapping(value = "uploadProductImage")
    public ResponseEntity<String> uploadProductImage(@RequestParam("file") MultipartFile file) {
        String productImageId = productImageService.save(new ProductImage()).getId().toString();

        System.out.println(file.getOriginalFilename());

        // Find image extension.
        String[] temp = file.getOriginalFilename().split("\\.");
        String imageExtension = temp[temp.length - 1];

        // Build image url efficiently with directory, uniqueIdentifier, extension.
        String fullPath = new StringBuilder(uploadDirectory).append("/").
                append(productImageId).append(".").append(imageExtension).toString();
        String partialPath = new StringBuilder(productImageId).append(".").append(imageExtension).toString();

        // Save image to disk
        try {
            ImageIO.write(ImageIO.read(new ByteArrayInputStream(file.getBytes())), "png", new File(fullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>(partialPath, new HttpHeaders(), HttpStatus.OK);
    }

    // Return actual image of a product
    @PostMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageWithMediaType(@RequestBody String imageUrl) throws IOException {
        InputStream in = getClass().getResourceAsStream("/uploads/" + imageUrl);
        if (in == null) {
            throw new IOException("Image with the URL: " + imageUrl + " does not exist.");
        }
        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "/vendorproductlist")
    public List<ProductVendorDTO> findAllVendorProductsDTO() {
        List<ProductVendorDTO> productVendorDTOList = new ArrayList<ProductVendorDTO>();



//        RestTemplate restTemplate = new RestTemplate();
//        List<VendorProductDTO> vendorProductDTOList = restTemplate.getForObject("USERMANAGEMENT-SERVICE", ArrayList.class);
//        for (VendorProductDTO)

        return productVendorDTOList;
    }

    @PostMapping(value = "/productNames")
    public ResponseEntity productNames(@RequestBody List<Long> idList) {
        List<String> productNameList = new ArrayList<>();
        for( Long id : idList) {
            Product product = productService.findById(id);
            if(product != null) {
                productNameList.add(product.getName());
            }
        }
        return new ResponseEntity(productNameList, new HttpHeaders(), HttpStatus.OK);
    }
}
