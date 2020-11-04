package com.bigcart.productservice.bigcartproductservice.Controller;

import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Services.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class CategoryControllerTest {


    @Mock
    private CategoryService categoryService;
    @InjectMocks
    CategoryController conroller;

    @Test
    void findAllCategories() {



    }

    @Test
    void addCategory() {
    }

    @Test
    void changeCategoryName() {
    }

    @Test
    void getCategory() {


        Category cat = new Category();
        cat =categoryService.findById(1);
        assertEquals(cat,categoryService.findById( 1));


    }

    @Test
    void deleteCategory() {
    }
}