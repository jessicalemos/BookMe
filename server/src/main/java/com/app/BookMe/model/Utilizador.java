package com.app.BookMe.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Utilizador {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
	private int iD;
	private String nome;
    @Column(unique = true)
	private String email;
	private String password;
	private String telemovel;

	public void editaPerfil(String nome, String password, String telemovel) {
		throw new UnsupportedOperationException();
	}
}