package com.bigcart.productservice.bigcartproductservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long review_id;
    private Long userId;
    private String review;
    private Integer rating;

    @ManyToOne
    ProductVendor productVendor;

    public Review() {
    }

    public Long getReview_id() {
        return review_id;
    }

    public void setReview_id(Long id) {
        this.review_id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
