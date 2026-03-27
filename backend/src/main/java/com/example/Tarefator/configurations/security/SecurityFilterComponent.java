package com.example.Tarefator.configurations.security;

import com.example.Tarefator.repositories.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilterComponent extends OncePerRequestFilter {

    private final TokenToolService tokenService;
    private final AppUserRepository userRepository;

    public SecurityFilterComponent(TokenToolService tokenService,AppUserRepository userRepository){
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var jwtToken = recoverToken(request);

        if (jwtToken != null) {
            var subject = tokenService.getTokenSubject(jwtToken);
            var user = userRepository.findByEmail(subject)
                    .orElseThrow(()->new RuntimeException("Usuário não encontrado."));

            var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    private String recoverToken (HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ","");
        }

        return null;
    }
}
