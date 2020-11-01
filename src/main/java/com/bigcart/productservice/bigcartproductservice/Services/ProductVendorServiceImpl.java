package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.DTO.ProductForAdminDTO;
import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendor;
import com.bigcart.productservice.bigcartproductservice.Model.ProductVendorCKey;
import com.bigcart.productservice.bigcartproductservice.Repository.CategoryRepository;
import com.bigcart.productservice.bigcartproductservice.Repository.ProductVendorRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductVendorServiceImpl implements ProductVendorService {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductVendorRepository productVendorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<ProductVendor> findAll() {
        return productVendorRepository.findAll();
    }

    @Override
    public List<ProductVendor> findAllByVendorId(Long id) {
        return productVendorRepository.findAllByVendorId(id);
    }

    @Override
    public List<ProductForAdminDTO> productToProductDTO(List<ProductVendor> productVendorList, String status) {

        List<ProductForAdminDTO> productForAdminDTOList = new ArrayList<>();

        for (ProductVendor productVendor : productVendorList) {
            if (productVendor.getStatus().equals(status)) {
                Product product = productService.findById(productVendor.getProductId());
                Category category = categoryService.findById(product.getCategoryId());
                ProductForAdminDTO productForAdminDTO = new ProductForAdminDTO(
                        productVendor.getRequestDate(), productVendor.getApprovalDate(),
                        productVendor.getModificationDate(), category.getCategoryId(),
                        category.getName(), productVendor.getPrice(), productVendor.getQuantity(),
                        productVendor.getVendorId(),
                        "Microsoft", product.getName(),
                        productVendor.getProductId() + "-" + productVendor.getVendorId()
                );
                productForAdminDTOList.add(productForAdminDTO);
            }
        }

        return productForAdminDTOList;
    }

    @Override
    public ProductVendor findById(Long productId, Long vendorId) {
        return productVendorRepository.findById(new ProductVendorCKey(productId, vendorId)).orElse(null);
    }

    @Override
    public ProductVendor findByVendorId(Long vendorId) {
        return productVendorRepository.findByVendorId(vendorId);
    }

    @Override
    public ProductVendor findByCategoryId(Long CategoryId) {
        return null;
    }

    @Override
    public ProductVendor save(ProductVendor productVendor) {
        ProductVendor pv = productVendorRepository.findById(new ProductVendorCKey(productVendor.getProductId(), productVendor.getVendorId())).orElse(null);
        if (pv == null) {
            return null;
        }
        return productVendorRepository.save(productVendor);
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
