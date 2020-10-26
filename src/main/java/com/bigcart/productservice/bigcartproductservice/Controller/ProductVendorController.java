
package com.bigcart.productservice.bigcartproductservice.Controller;
        import com.bigcart.productservice.bigcartproductservice.DTO.ListItmeDTO;
        import com.bigcart.productservice.bigcartproductservice.Services.ProductVendorService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

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
