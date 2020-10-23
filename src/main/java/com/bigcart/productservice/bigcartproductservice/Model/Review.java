package com.bigcart.productservice.bigcartproductservice.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    private Integer id;
    private Integer userId;
    private String review;
    private Integer rating;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
