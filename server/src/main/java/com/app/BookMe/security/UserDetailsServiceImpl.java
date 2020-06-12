package com.app.BookMe.security;

import com.app.BookMe.model.Utilizador;
import com.app.BookMe.repositories.UtilizadorRep;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UtilizadorRep utiRepository;
    
    public UserDetailsServiceImpl(UtilizadorRep atorRepository) {
        this.utiRepository = atorRepository;
    }
    
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilizador> applicationUser = utiRepository.findByEmail(username);

        Utilizador appUser = null;
        if(applicationUser.isPresent()){
           appUser = applicationUser.get();
        } else {
            throw new UsernameNotFoundException(username);
        }

        return new User(appUser.getEmail(), appUser.getPassword(), emptyList());
    }
}