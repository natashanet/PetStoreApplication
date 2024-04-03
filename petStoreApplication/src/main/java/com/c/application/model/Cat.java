package com.c.application.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
public class Cat extends Pet {
    public void calculatePrice() {
        int ageInYears = calculateAgeInYears();
        setPrice(ageInYears); // Set price directly as age in years
    }
}