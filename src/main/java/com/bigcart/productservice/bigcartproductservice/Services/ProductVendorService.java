package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.DTO.ListItmeDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.ProductForAdminDTO;
import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendor;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendorCKey;

import java.util.List;

// return product vendor and combine in controller
public interface ProductVendorService {
    List<ProductVendor> findAll();
    List<ProductVendor> findAllByVendorId(Long id);
    List<ProductForAdminDTO> productToProductDTO (List<ProductVendor> productVendorList, String status);

    ProductVendor findById(Long productId, Long vendorId) ;
    ProductVendor findByVendorId(Long VendorId);
    ProductVendor findByCategoryId(Long CategoryId);

    ProductVendor save(ProductVendor productVendor);
    ProductVendor update(ProductVendor productVendor);
    ProductVendor delete(ProductVendor productVendor);




//    public boolean removeProductV(ListItmeDTO items);
//
//    public ProductVendor editProductV(long productID, Product edit_product);
//
//    public ProductVendor deleteProductV(long productId);
}
