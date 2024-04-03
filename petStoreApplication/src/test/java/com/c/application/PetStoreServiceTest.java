package com.c.application;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
public class PetStoreServiceTest {

    @Mock
    private UserRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetStoreService petStoreService;

    private Owner owner;
    private List<Pet> pets;

    private List<Pet> pets2;

    @BeforeEach
    public void setUp() {
        owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Natasha");
        owner.setLastName("N");
        owner.setBudget(200);

        Pet cat1 = new Pet();
        cat1.setPrice(2);
        cat1.setId(1L);
        cat1.setName("TomCat");
        cat1.setType(PetType.CAT);
        cat1.setOwner(null);

        Pet cat2 = new Pet();
        cat2.setId(2L);
        cat2.setName("Mache");
        cat2.setType(PetType.CAT);
        cat2.setOwner(owner);
        cat2.setPrice(3);

        Pet dog1 = new Pet();
        dog1.setId(2L);
        dog1.setName("Kuche");
        dog1.setType(PetType.DOG);
        dog1.setOwner(null);
        dog1.setPrice(2);

        Pet dog2 = new Pet();
        dog2.setId(2L);
        dog2.setName("Lesi");
        dog2.setType(PetType.DOG);
        dog2.setOwner(null);
        dog2.setPrice(5);

        pets = Arrays.asList(cat1, dog1, cat2, dog2);

        List<Pet> pets2 = Collections.singletonList(cat1);
    }

    @Test
    public void testSuccess() {
        when(ownerRepository.findAll()).thenReturn(Collections.singletonList(owner));
        when(petRepository.findAll()).thenReturn(pets);

        petStoreService.buyPets();

        verify(ownerRepository, times(1)).findAll();
    }


    @Test
    public void testNoPetsAvailable() {
        when(ownerRepository.findAll()).thenReturn(Collections.singletonList(owner));
        when(petRepository.findAll()).thenReturn(Collections.emptyList());

        petStoreService.buyPets();

        verify(ownerRepository, times(1)).findAll();
        verify(petRepository, times(1)).findAll();
        verify(petRepository, never()).save(any(Pet.class));
    }

    @Test
    public void testOwnersAndPetsAvailableButNoPetsToBuy() {
        when(ownerRepository.findAll()).thenReturn(Collections.singletonList(owner));
        when(petRepository.findAll()).thenReturn(pets);

        pets.forEach(pet -> pet.setOwner(owner));

        petStoreService.buyPets();

        verify(ownerRepository, times(1)).findAll();
        verify(petRepository, times(1)).findAll();
        verify(petRepository, never()).save(any(Pet.class));
    }

}