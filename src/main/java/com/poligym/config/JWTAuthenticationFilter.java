package com.poligym.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poligym.models.Users;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    // Injetando dependencias
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            Users user = new ObjectMapper().readValue(request.getInputStream(), Users.class);
            return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                String users = ((Users) authResult.getPrincipal()).getEmail();
                String token = Jwts.builder()
                                  .setSubject(users)
                                  .setExpiration(new Date(System.currentTimeMillis() + com.poligym.config.SecurityConstants.EXPIRATION_TIME))
                                  .signWith(SignatureAlgorithm.HS512, com.poligym.config.SecurityConstants.SECRET)
                                  .compact();

                response.setHeader(com.poligym.config.SecurityConstants.HEADER_STRING, com.poligym.config.SecurityConstants.TOKEN_PREFIX + token);
    }
    
}
