package com.example.Tarefator.services;

import com.example.Tarefator.configurations.security.TokenToolService;
import com.example.Tarefator.dtos.AuthLoginDTO;
import com.example.Tarefator.dtos.AuthRegisterDTO;
import com.example.Tarefator.dtos.UserDataDTO;
import com.example.Tarefator.models.AppUser;
import com.example.Tarefator.repositories.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final AppUserRepository repository;
    private final AuthenticationManager authManager;
    private final TokenToolService tokenService;
    public AuthService(AppUserRepository repository,AuthenticationManager authManager,TokenToolService tokenService){
        this.repository = repository;
        this.authManager = authManager;
        this.tokenService = tokenService;
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

    public String userLogon(@Valid AuthLoginDTO loginData) {
        var userData = new UsernamePasswordAuthenticationToken(loginData.email(),loginData.password());
        var userAuthentication = authManager.authenticate(userData);
        return tokenService.generateToken((AppUser) userAuthentication.getPrincipal());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}
