package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.DTO.ListDto;
import com.bigcart.productservice.bigcartproductservice.Model.Product;

import java.util.List;

public interface ProductVendorService {

    public boolean removeProductV(ListDto items);

    public Product getProductV(long productId) ;

    public List<Product> getProductV();

    public Product editProductV(long productID, Product edit_product);

    public Product deleteProductV(long productId);
}
