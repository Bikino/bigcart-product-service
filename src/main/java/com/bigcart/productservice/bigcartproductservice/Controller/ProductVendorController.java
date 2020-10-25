
package com.bigcart.productservice.bigcartproductservice.Controller;
        import com.bigcart.productservice.bigcartproductservice.DTO.ListItmeDTO;
        import com.bigcart.productservice.bigcartproductservice.Model.Product;
        import com.bigcart.productservice.bigcartproductservice.Services.ProductService;
        import com.bigcart.productservice.bigcartproductservice.Services.ProductVendorService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductVendorController {
    @Autowired
    ProductVendorService productService;

    @PostMapping("/remove")
    public void removeProducts(@RequestBody ListItmeDTO listDto) {
        productService.removeProductV(listDto);
    }



}
