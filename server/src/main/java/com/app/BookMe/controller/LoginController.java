package com.app.BookMe.controller;

import com.app.BookMe.model.*;
import com.app.BookMe.repositories.UtilizadorRep;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {
    @Autowired
    private UtilizadorRep ur;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody ObjectNode utilizador) {
        String email = utilizador.get("email").asText();
        String password = utilizador.get("password").asText();

        Optional<Utilizador> currentUt = ur.findByEmail(email);

        if(!currentUt.isPresent()){
            return null;
        }

        Utilizador u = currentUt.get();
        System.out.println("Fetch Utilizador");
        System.out.println(u);

        System.out.println("Utilizador: " );
        System.out.println(u.getEmail());
        System.out.println(passwordEncoder.encode(password));

        if(u != null) {

            if (passwordEncoder.matches(password, u.getPassword())) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                System.out.println(auth.getAuthorities());
                System.out.println(auth.toString());

                return "Login realizado com sucesso!";
            }
        }
        return null;
    }
}
