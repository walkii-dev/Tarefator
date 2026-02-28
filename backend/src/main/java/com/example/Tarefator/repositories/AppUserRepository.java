package com.example.Tarefator.repositories;

import com.example.Tarefator.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    boolean findByEmail(String registerUserEmail);
}
