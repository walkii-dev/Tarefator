package com.example.Tarefator.services;

import com.example.Tarefator.configurations.security.TokenToolService;
import com.example.Tarefator.dtos.AuthLoginDTO;
import com.example.Tarefator.dtos.AuthRegisterDTO;
import com.example.Tarefator.dtos.UserDataDTO;
import com.example.Tarefator.models.AppUser;
import com.example.Tarefator.repositories.AppUserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(TaskService.class.getName());

    private final AppUserRepository repository;
    private final AuthenticationManager authManager;
    private final TokenToolService tokenService;
    private final PasswordEncoder encoder;

    public AuthService(AppUserRepository repository,
                       @Lazy AuthenticationManager authManager,
                       TokenToolService tokenService,
                       PasswordEncoder encoder){
        this.repository = repository;
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.encoder = encoder;
    }

    public AppUser registerUser (@Valid AuthRegisterDTO registerData){
            var newuser = new AppUser(registerData, encoder);
            logger.info("adding new user on database...");
        try {
            repository.save(newuser);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("this email is already registered.");
        }
        return newuser;

    }

    public String userLogon(@Valid AuthLoginDTO loginData) {
        logger.info("Logging user on app...");
        var userData = new UsernamePasswordAuthenticationToken(loginData.email(),loginData.password());
        var userAuthentication = authManager.authenticate(userData);
        logger.info("generating token...");
        return tokenService.generateToken((AppUser) userAuthentication.getPrincipal());
    }

    // encontrar algum fim pra isso...
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}
