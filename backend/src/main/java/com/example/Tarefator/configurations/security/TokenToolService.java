package com.example.Tarefator.configurations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.Tarefator.models.AppUser;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenToolService {

    private final String issuer = "Tarefator_API";

    // futuramente é uma variável de ambiente no application.yaml
    private String secret = "EllenMaria";

    private Algorithm mainAlgorithm = Algorithm.HMAC256(secret);

    public String generateToken(AppUser user){
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getEmail())
                    .withExpiresAt(tokenExpirationDate())
                    .sign(mainAlgorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error on generate token to user: "+exception);
        }
    }

    public String getTokenSubject(String token){
        try {
            return JWT.require(mainAlgorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error on get subject on token.");
        }
    }

    //define o tempo de duração do token.
    private Instant tokenExpirationDate(){
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }

}
