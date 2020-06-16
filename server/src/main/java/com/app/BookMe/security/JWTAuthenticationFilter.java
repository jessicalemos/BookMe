package com.app.BookMe.security;

import com.app.BookMe.model.Utilizador;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.app.BookMe.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {

        System.out.println("Attempting authentication");
        try {
            Utilizador creds = new ObjectMapper().readValue(req.getInputStream(), Utilizador.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(),
                    creds.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {


        System.out.println("Valid authentication");

        String[] role = auth.getAuthorities().toString().replace("[", "")
                                                        .replace("]", "")
                                                        .split(",");

        System.out.println(role[0]);
        System.out.println(role[1]);
        System.out.println(auth.getName());
        String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
                .claim("username", auth.getName()) // o email
                .claim("role", role[0]) // tipo de utilizador
                .claim("id", role[1]) //id do utilizador
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();

        System.out.println("Token");
        System.out.println(token);

        res.getWriter().write("{\"token\" : \"" + token + "\"}");
        res.addHeader("Content-Type", "application/json");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}