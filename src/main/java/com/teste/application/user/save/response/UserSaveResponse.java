package com.teste.application.user.save.response;

import java.io.Serializable;

public class UserSaveResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String cpf;

	public UserSaveResponse() { }
	
	public UserSaveResponse(Long id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

}
