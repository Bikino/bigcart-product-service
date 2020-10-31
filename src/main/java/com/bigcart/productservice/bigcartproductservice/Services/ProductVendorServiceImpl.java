package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.ProductVendor;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendorCKey;
import com.bigcart.productservice.bigcartproductservice.Repository.ProductVendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class ProductVendorServiceImpl implements ProductVendorService {
    @Autowired
    ProductVendorRepository productVendorRepository;

    @Override
    public List<ProductVendor> findAll() {
        return productVendorRepository.findAll();
    }

    @Override
    public ProductVendor findById(Long productId, Long vendorId) {
        return  productVendorRepository.findById(new ProductVendorCKey(productId, vendorId)).orElse(null);
    }

    @Override
    public ProductVendor save(ProductVendor productVendor) {
        ProductVendor pv = productVendorRepository.findById(new ProductVendorCKey(productVendor.getProductId(), productVendor.getVendorId())).orElse(null);
        if(pv == null) {
            productVendor.setDateAdded(LocalDateTime.now());
            productVendor.setDateModified(LocalDateTime.now());
            return productVendorRepository.save(productVendor);
        }
            return null;
    }

    @Override
    public ProductVendor update(ProductVendor productVendor) {
        return null;
    }

    @Override
    public ProductVendor delete(ProductVendor productVendor) {
        return null;
    }

    //    @Override
//    public boolean removeProductV(ListItmeDTO items) {
//
//        for (ItemDTO i:items.getList()
//             ) {
//
//            ProductVendorCKey key= new ProductVendorCKey(i.getVendorId(),i.getItemtId());
//            ProductVendor PV=  vendorProductRepository.findById(key).get();
//            PV.decQty(i.getQuantity());
//            vendorProductRepository.save(PV);
//        }
//        return false;
//    }
}
