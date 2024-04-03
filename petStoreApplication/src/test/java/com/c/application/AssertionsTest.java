package com.c.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.c.application.enums.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.c.application.model.Owner;
import com.c.application.model.Pet;
import com.c.application.repo.UserRepository;
import com.c.application.repo.PetRepository;
import com.c.application.service.PetStoreService;

@ExtendWith(MockitoExtension.class)
public class AssertionsTest {

    @Mock
    private UserRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetStoreService petStoreService;

    private Owner owner;
    private List<Pet> pets;

    @BeforeEach
    public void initializeSet() {

        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Mache");
        pet1.setType(PetType.CAT);
        pet1.setPrice(2);
        pet1.setOwner(null);

        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setName("Kuche");
        pet2.setType(PetType.DOG);
        pet2.setOwner(null);
        pet2.setPrice(3);

        owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Natasha");
        owner.setLastName("Net");
        owner.setBudget(245);


        pets = Arrays.asList(pet1, pet2);
    }

    @Test
    public void testSuccess() {
        when(ownerRepository.findAll()).thenReturn(Collections.singletonList(owner));
        when(petRepository.findAll()).thenReturn(pets);

        petStoreService.buyPets();

        assertEquals(1, ownerRepository.findAll().size());
        assertEquals(2, petRepository.findAll().size());
    }

    @Test
    public void testNoPetsAvailable() {
        when(ownerRepository.findAll()).thenReturn(Collections.singletonList(owner));
        when(petRepository.findAll()).thenReturn(Collections.emptyList());

        petStoreService.buyPets();

        assertEquals(1, ownerRepository.findAll().size());
        assertEquals(0, petRepository.findAll().size());
    }

    @Test
    public void testOwnerNotFound() {
        when(ownerRepository.findAll()).thenReturn(Collections.emptyList());
        when(petRepository.findAll()).thenReturn(pets);

        petStoreService.buyPets();

        assertEquals(0, ownerRepository.findAll().size());
        assertEquals(2, petRepository.findAll().size());
    }

    @Test
    public void testOwnerWithBudget() {
        owner.setBudget(50);

        when(ownerRepository.findAll()).thenReturn(Collections.singletonList(owner));
        when(petRepository.findAll()).thenReturn(pets);

        petStoreService.buyPets();

        assertEquals(1, ownerRepository.findAll().size());
        assertEquals(2, petRepository.findAll().size());
    }

    @Test
    public void testOwnerWithNoBudget() {
        owner.setBudget(0);

        when(ownerRepository.findAll()).thenReturn(Collections.singletonList(owner));
        when(petRepository.findAll()).thenReturn(pets);

        petStoreService.buyPets();

        assertEquals(1, ownerRepository.findAll().size());
        assertEquals(2, petRepository.findAll().size());
    }
}
