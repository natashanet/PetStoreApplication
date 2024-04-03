package com.c.application.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
public class Dog extends Pet {
    private int rating;

    public int getRating() {

        return rating;
    }

    public void setRating(int rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating should be between 0 and 10!");
        }
        this.rating = rating;
    }

    public void calculatePrice() {
        int ageInYears = calculateAgeInYears();
        int ratingPrice = rating;
        int totalPrice = ageInYears + ratingPrice;

        setPrice(totalPrice);
    }
}