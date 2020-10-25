package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.DTO.ItemDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.ListItmeDTO;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendor;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendorCKey;
import com.bigcart.productservice.bigcartproductservice.Repository.VendorProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductVendorServiceImpl implements ProductVendorService {
    @Autowired
    VendorProductRepository productRepository;
    @Override
    public boolean removeProductV(ListItmeDTO items) {

        for (ItemDTO i:items.getList()
             ) {
            //TODO handle sold out
            ProductVendorCKey key= new ProductVendorCKey(i.getVendorId(),i.getItemtId());
            ProductVendor PV=  productRepository.findById(key).get();
            PV.decQty(i.getQuantity());
            productRepository.save(PV);
        }


        return false;
    }

    @Override
    public Product getProductV(long productId) {
        return null;
    }

    @Override
    public List<Product> getProductV() {
        return null;
    }

    @Override
    public Product editProductV(long productID, Product edit_product) {
        return null;
    }

    @Override
    public Product deleteProductV(long productId) {
        return null;
    }


}
