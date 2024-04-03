package com.c.application.repo;

import com.c.application.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Owner, Long> {
}