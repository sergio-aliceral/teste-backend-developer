package com.teste.application.user.save.request;

import static com.teste.utils.Utils.onlyNumber;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.teste.validation.CpfValid;

public class UserSaveRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 1, max = 50)
	private String nome;

	@CpfValid
	private String cpf;

	public UserSaveRequest() { }

	public UserSaveRequest(String nome, String cpf) {
		setNome(nome);
		setCpf(cpf);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		this.cpf = onlyNumber(cpf);
	}

	public String getCpf() {
		return cpf;
	}

}
