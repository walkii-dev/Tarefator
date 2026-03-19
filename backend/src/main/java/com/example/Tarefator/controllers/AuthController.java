package com.example.Tarefator.controllers;

import com.example.Tarefator.dtos.AuthLoginDTO;
import com.example.Tarefator.dtos.AuthRegisterDTO;
import com.example.Tarefator.dtos.TokenDataDTO;
import com.example.Tarefator.dtos.UserDataDTO;
import com.example.Tarefator.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity userLogon (@RequestBody @Valid AuthLoginDTO loginData){
        var generatedToken = authService.userLogon(loginData);
        return ResponseEntity.ok(new TokenDataDTO(generatedToken));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerNewUser (@RequestBody @Valid AuthRegisterDTO registerData, UriComponentsBuilder uriBuilder){
        var user = authService.registerUser(registerData);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        // precisa ter um serviço de envio de e-mail aqui para fazer o login
    return ResponseEntity.created(uri).body(new UserDataDTO(user));
    }
}
