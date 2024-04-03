package com.c.application.controller;

import com.c.application.model.Pet;
import com.c.application.model.Owner;
import com.c.application.service.PetStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PetStoreController {

    private static final int MAX_PETS = 20;
    private static final int MAX_USERS = 10;
    @Autowired
    private PetStoreService petStoreService;

    @PostMapping("/users")
    public Owner createUsers(@RequestBody Owner user) {
        List<Owner> existingUsers = listUsers();
        if (existingUsers.size() >= MAX_USERS) {
            throw new IllegalStateException("Maximum number of users reached.");
        }
        return petStoreService.createUsers(user);
    }

    @PostMapping("/pets")
    public Pet createPet(@RequestBody Pet pet) {
        List<Pet> existingPets = listPets();

        if (existingPets.size() >= MAX_PETS) {
            throw new IllegalStateException("Maximum number of pets reached.");
        }

        return petStoreService.createPet(pet);
    }

    @GetMapping("/users")
    public List<Owner> listUsers() {
        return petStoreService.listUsers();
    }

    @GetMapping("/pets")
    public List<Pet> listPets() {
        return petStoreService.listPets();
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyPets() {
        try {
            petStoreService.buyPets();
            return ResponseEntity.ok("The pets are bought successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to buy pets: " + e.getMessage());
        }
    }

    @GetMapping("/buyHistory")
    public List<PetStoreService.BuyHistoryEntry> getBuyHistory() {
        return petStoreService.getBuyHistory();
    }
}
