package com.app.BookMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Funcionario.class, name = "funcionario"),
		@JsonSubTypes.Type(value = Requisitante.class, name = "requisitante"),
		@JsonSubTypes.Type(value = Administrador.class, name = "administrador"),
		@JsonSubTypes.Type(value = Responsavel.class, name = "responsavel")
})
public abstract class Utilizador implements UserDetails {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
	private long iD;
    @Column(unique = true)
	private String email;
	private String password;

    public Utilizador() {

    }

    public Utilizador(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public long getiD() {
        return iD;
    }

    public void setiD(long iD) {
        this.iD = iD;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void editaPerfil(String nome, String password, String telemovel) {
		throw new UnsupportedOperationException();
	}

    @Override
    @JsonIgnore
    public String getUsername(){
        return email;
    }
    @Override
    @JsonIgnore
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}