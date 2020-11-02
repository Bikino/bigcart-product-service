package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.DTO.ProductForAdminDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.VendorNameDTO;
import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProductCKey;
import com.bigcart.productservice.bigcartproductservice.Repository.CategoryRepository;
import com.bigcart.productservice.bigcartproductservice.Repository.VendorProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorProductServiceImpl implements VendorProductService {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    VendorProductRepository vendorProductRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<VendorProduct> findAll() {
        return vendorProductRepository.findAll();
    }

    @Override
    public List<VendorProduct> findAllByVendorId(Long id) {
        return vendorProductRepository.findAllByVendorId(id);
    }

    @Override
    public List<ProductForAdminDTO> productToProductDTOList(List<VendorProduct> vendorProductList, String status) {

        List<ProductForAdminDTO> productForAdminDTOList = new ArrayList<>();

        for (VendorProduct vendorProduct : vendorProductList) {
            if (vendorProduct.getStatus().equals(status)) {
                Product product = productService.findById(vendorProduct.getProductId());
                Category category = categoryService.findById(product.getCategoryId());
                ProductForAdminDTO productForAdminDTO = new ProductForAdminDTO(
                        vendorProduct.getRequestDate(), vendorProduct.getApprovalDate(),
                        vendorProduct.getModificationDate(), category.getCategoryId(),
                        category.getName(), vendorProduct.getPrice(), vendorProduct.getQuantity(),
                        vendorProduct.getVendorId(),
                        getVendorNameByVendorId(vendorProduct.getVendorId()), product.getName(),
                        vendorProduct.getVendorId() + "-" + vendorProduct.getProductId()
                );
                productForAdminDTOList.add(productForAdminDTO);
            }
        }

        return productForAdminDTOList;
    }

    @Override
    public List<ProductForAdminDTO> productToProductDTOList(List<VendorProduct> vendorProductList) {
        List<ProductForAdminDTO> productForAdminDTOList = new ArrayList<>();

        for (VendorProduct vendorProduct : vendorProductList) {
                Product product = productService.findById(vendorProduct.getProductId());
                Category category = categoryService.findById(product.getCategoryId());
                ProductForAdminDTO productForAdminDTO = new ProductForAdminDTO(
                        vendorProduct.getRequestDate(), vendorProduct.getApprovalDate(),
                        vendorProduct.getModificationDate(), category.getCategoryId(),
                        category.getName(), vendorProduct.getPrice(), vendorProduct.getQuantity(),
                        vendorProduct.getVendorId(),
                        getVendorNameByVendorId(vendorProduct.getVendorId()), product.getName(),
                        vendorProduct.getProductId() + "-" + vendorProduct.getVendorId()
                );
                productForAdminDTOList.add(productForAdminDTO);
        }
        return productForAdminDTOList;
    }

    @Override
    public String getVendorNameByVendorId(String vendorId) {
        RestTemplate restTemplate = new RestTemplate();
        VendorNameDTO v;
        try {
            v = restTemplate.getForObject("http://localhost:9988/vendor/" + vendorId, VendorNameDTO.class);
        }
        catch (Exception e) {
            return "Unknown";
        }
        return v.getCompanyName();
    }

    @Override
    public String getVendorNameByVendorId(Long vendorId) {
        RestTemplate restTemplate = new RestTemplate();
        VendorNameDTO v;
        try {
            v = restTemplate.getForObject("http://localhost:9988/vendor/" + vendorId.toString(), VendorNameDTO.class);
        }
        catch (Exception e) {
            return "Unknown";
        }
        return v.getCompanyName();
    }

    @Override
    public VendorProduct findById(Long vendorId, Long productId) {
        return vendorProductRepository.findById(new VendorProductCKey(vendorId, productId)).orElse(null);
    }

    @Override
    public VendorProduct findByVendorId(Long vendorId) {
        return vendorProductRepository.findByVendorId(vendorId);
    }

    @Override
    public VendorProduct findByCategoryId(Long CategoryId) {
        return null;
    }

    @Override
    public ProductForAdminDTO productToProductDTO(VendorProduct vendorProduct, String status) {

        if(vendorProduct == null) {
            return null;
        }

        ProductForAdminDTO productForAdminDTO = null;

        if (vendorProduct.getStatus().equals(status)) {
            Product product = productService.findById(vendorProduct.getProductId());
            Category category = categoryService.findById(product.getCategoryId());


            productForAdminDTO = new ProductForAdminDTO(
                    vendorProduct.getRequestDate(), vendorProduct.getApprovalDate(),
                    vendorProduct.getModificationDate(), category.getCategoryId(),
                    category.getName(), vendorProduct.getPrice(), vendorProduct.getQuantity(),
                    vendorProduct.getVendorId(),
                    getVendorNameByVendorId(vendorProduct.getVendorId()), product.getName(),
                    vendorProduct.getProductId() + "-" + vendorProduct.getVendorId()
            );
        }
        return productForAdminDTO;
    }

    @Override
    public VendorProduct save(VendorProduct vendorProduct) {
        VendorProduct pv = vendorProductRepository.findById(new VendorProductCKey(vendorProduct.getVendorId(), vendorProduct.getProductId())).orElse(null);
        if (pv == null) {
            return null;
        }
        return vendorProductRepository.save(vendorProduct);
    }

    @Override
    public VendorProduct update(VendorProduct vendorProduct) {
        return null;
    }

    @Override
    public VendorProduct delete(VendorProduct vendorProduct) {
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
