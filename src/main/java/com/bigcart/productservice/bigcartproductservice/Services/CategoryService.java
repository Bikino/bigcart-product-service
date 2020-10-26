package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Category;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category Category);

    public Category getCategory(long CategoryId) ;

    public List<Category> getCategorys();

    public Category editCategory(long CategoryID, Category edit_Category);

    public Boolean deleteCategory(long CategoryId);
}
