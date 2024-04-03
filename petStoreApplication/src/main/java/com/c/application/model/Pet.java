package com.c.application.model;
import com.c.application.enums.PetType;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pet {

    private static final int MAX_PETS = 20;
    private static int createdPets = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
   // One pet can only belong to one owner.

    // @NotNull here should not be used - "When pets are created/added for the first time in the store they don’t have any owner."
    // so NULL values for the Owner are allowed.
    private Owner owner;
    private String name;
    @Enumerated(EnumType.STRING)
    private PetType type;
    private String description;
    private LocalDate dateOfBirth;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {

        return type;
    }

    public void setType(PetType type) {

        this.type = type;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public LocalDate getDateOfBirth() {

        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int calculateAgeInYears() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - dateOfBirth.getYear();
    }
}
