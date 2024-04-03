package com.c.application.repo;

import com.c.application.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PetRepository extends JpaRepository<Pet, Long> {
}