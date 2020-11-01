package com.bigcart.productservice.bigcartproductservice.Controller;
import com.bigcart.productservice.bigcartproductservice.Model.Category;

import com.bigcart.productservice.bigcartproductservice.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/")
    public ResponseEntity findAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity(categoryList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {

        HttpHeaders headers = new HttpHeaders();

        if (category == null) {
            return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
        }
        categoryService.save(category);

       return new ResponseEntity<Category>(category, headers, HttpStatus.CREATED);

    }




    @GetMapping(value = "/{CategoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable long CategoryId) {

        Category Category = categoryService.findById(CategoryId);

        if (Category == null) {

            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Category>(Category, HttpStatus.OK);
    }

//    @PutMapping
//    public ResponseEntity<Category> editCategory(@RequestBody Category Category) {
//
//        HttpHeaders headers = new HttpHeaders();
//        Category Category_toEdit = categoryService.findById(Category.getCategoryId());
//
//        if (Category_toEdit == null) {
//
//            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
//        }
//
//        Category updatedCategory = categoryService.update(Category.getCategoryId(), Category);
//
//        return new ResponseEntity<Category>(updatedCategory, headers, HttpStatus.OK);
//    }

    @DeleteMapping(value = "/{CategoryId}")
    public  ResponseEntity deleteCategory(@PathVariable long CategoryId) {

        return new ResponseEntity( categoryService.delete(CategoryId)? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
