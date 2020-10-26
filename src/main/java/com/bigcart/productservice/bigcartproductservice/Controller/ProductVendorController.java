
package com.bigcart.productservice.bigcartproductservice.Controller;
        import com.bigcart.productservice.bigcartproductservice.DTO.ListDto;
        import com.bigcart.productservice.bigcartproductservice.Services.ProductVendorService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductVendorController {
    @Autowired
    ProductVendorService productService;

    @PostMapping("/remove")
    public void removeProducts(@RequestBody ListDto listDto) {
        //productService.removeProductV(listDto);
        System.out.println(listDto);
    }



}
