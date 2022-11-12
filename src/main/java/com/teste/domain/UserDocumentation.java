package com.teste.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.teste.emums.TipoDocumentoEmum;

@Entity
public class UserDocumentation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	private byte [] document;
	
	@Enumerated(EnumType.STRING)
	private TipoDocumentoEmum tipoDocumento;

	@ManyToOne
	private User user;
	
	public UserDocumentation() { }

	public UserDocumentation(byte [] document, TipoDocumentoEmum tipoDocumento, User user) {
		this.document = document;
		this.tipoDocumento = tipoDocumento;
		this.user = user;
	}

	public UserDocumentation(Long id, byte [] document, TipoDocumentoEmum tipoDocumento, User user) {
		this.id = id;
		this.document = document;
		this.tipoDocumento = tipoDocumento;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	
	public byte[] getDocument() {
		return document;
	}
	
	public TipoDocumentoEmum getTipoDocumento() {
		return tipoDocumento;
	}
	
	public User getUser() {
		return user;
	}
	
}
