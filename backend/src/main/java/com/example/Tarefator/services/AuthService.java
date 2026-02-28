package com.example.Tarefator.services;

import com.example.Tarefator.dtos.AuthRegisterDTO;
import com.example.Tarefator.dtos.UserDataDTO;
import com.example.Tarefator.models.AppUser;
import com.example.Tarefator.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository repository;
    public AuthService(AppUserRepository repository){
        this.repository = repository;
    }


/*
o servico verificara se há um usuário no banco de dados duplicado (mesmo email) e retornara erro se tiver
 */
    public AppUser registerUser (AuthRegisterDTO registerData){

        var newuser = new AppUser(registerData);

            repository.save(newuser);

        System.out.println("usuario salvo");

        return newuser;

    }
}
