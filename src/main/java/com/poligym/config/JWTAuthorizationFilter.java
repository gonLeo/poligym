package com.poligym.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poligym.service.JwtUserDetailsService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final JwtUserDetailsService jwtUserDetailsService;

    //construtor
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService ){
        super(authenticationManager);
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    //Sobrescrever metodo
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        String header = request.getHeader(com.poligym.config.SecurityConstants.HEADER_STRING);

        if(header == null || !header.startsWith(com.poligym.config.SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
        String token = request.getHeader(com.poligym.config.SecurityConstants.HEADER_STRING);

        if(token == null) return null;

        String userEmail = Jwts.parser().setSigningKey(com.poligym.config.SecurityConstants.SECRET)
                            .parseClaimsJws(token.replace(com.poligym.config.SecurityConstants.TOKEN_PREFIX, ""))
                            .getBody()
                            .getSubject();
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userEmail);

        return userEmail != null ? new UsernamePasswordAuthenticationToken(userEmail, null, userDetails.getAuthorities()) : null;
    }
}
