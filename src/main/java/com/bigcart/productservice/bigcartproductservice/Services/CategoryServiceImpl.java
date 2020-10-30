package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category) ;
    }

    @Override
    public Category getCategory(long ctegoryId) {
        return categoryRepository.findById(ctegoryId).get();
    }

    @Override
    public List<Category> getCategorys() {
        return categoryRepository.findAll();
    }

    @Override
    public Category editCategory(long categoryID, Category edit_Category) {
        Category category= categoryRepository.getOne(categoryID);
        if (category==null)
            return null;
        category.setName(category.getName());



        return categoryRepository.save(category);
    }

    @Override
    public Boolean deleteCategory(long categoryId) {

        if (getCategory( categoryId)==null)
            return false;
        categoryRepository.deleteById( categoryId);
        return true ;

    }
}
