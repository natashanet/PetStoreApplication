package com.c.application.service;

import com.c.application.model.*;
import com.c.application.repo.PetRepository;
import com.c.application.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetStoreService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    private List<BuyHistoryEntry> buyHistory = new ArrayList<>();

    @Transactional
    public Owner createUsers(Owner user) {
        return userRepository.save(user);
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Owner> listUsers() {
        return userRepository.findAll();
    }

    public List<Pet> listPets() {
        return petRepository.findAll();
    }

    @Transactional
    public synchronized void buyPets() {
        List<Owner> users = userRepository.findAll();
        int successfulBuys = 0;
        int failedBuys = 0;

        for (Owner user : users) {
            if (isUserAssignedToPet(user)) {
                continue;
            }

            double userBudget = user.getBudget();
            List<Pet> pets = petRepository.findAll();
            for (Pet pet : pets) {
                double petPrice = pet.getPrice();

                if (userBudget >= petPrice && pet.getOwner() == null) {
                    try {
                        user.setBudget(userBudget - petPrice);
                        pet.setOwner(user);
                        petRepository.save(pet);
                        successfulBuys++;
                        logBuyHistory(successfulBuys, failedBuys);

                        if (pet instanceof Cat) {
                            ((Cat) pet).calculatePrice();
                            System.out.println("Meow, cat " + pet.getName() + " has owner " + user.getFirstName());
                        } else if (pet instanceof Dog) {
                            ((Dog) pet).calculatePrice();
                            System.out.println("Woof, dog " + pet.getName() + " has owner " + user.getFirstName());
                        }
                        break;
                    } catch (DataIntegrityViolationException e) {
                        failedBuys++;
                        logBuyHistory(successfulBuys, failedBuys);
                    }
                } else {
                    failedBuys++;
                    logBuyHistory(successfulBuys, failedBuys);
                }
            }
            userRepository.save(user);
        }
    }

    private boolean isUserAssignedToPet(Owner user) {
        List<Pet> pets = petRepository.findByOwner(user);
        return !pets.isEmpty();
    }


    private void logBuyHistory(int successfulBuys, int failedBuys) {
        LocalDateTime now = LocalDateTime.now();
        BuyHistoryEntry entry = new BuyHistoryEntry(now, successfulBuys, failedBuys);
        buyHistory.add(entry);
    }

    public List<BuyHistoryEntry> getBuyHistory() {

        return buyHistory;
    }

    public static class BuyHistoryEntry {
        private LocalDateTime dateOfExecution;
        private int successfulBuys;
        private int failedBuys;

        public BuyHistoryEntry(LocalDateTime dateOfExecution, int successfulBuys, int failedBuys) {
            this.dateOfExecution = dateOfExecution;
            this.successfulBuys = successfulBuys;
            this.failedBuys = failedBuys;
        }

        public LocalDateTime getDateOfExecution() {
            return dateOfExecution;
        }

        public int getSuccessfulBuys() {
            return successfulBuys;
        }

        public int getFailedBuys() {
            return failedBuys;
        }
    }
}
