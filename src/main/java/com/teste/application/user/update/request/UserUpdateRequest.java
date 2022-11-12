package com.teste.application.user.update.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.teste.validation.CpfValid;

public class UserUpdateRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	@Size(min = 1, max = 50)
	private String nome;

	@CpfValid
	private String cpf;

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}

}
