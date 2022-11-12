package com.teste.application.documentation.update.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.teste.emums.TipoDocumentoEmum;

public class DocumentationUpdateRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	private MultipartFile document;

	@NotNull
	private TipoDocumentoEmum tipoDocumento;

	@NotNull
	private Long idUser;

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setDocument(MultipartFile document) {
		this.document = document;
	}
	
	public MultipartFile getDocument() {
		return document;
	}

	public TipoDocumentoEmum getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(TipoDocumentoEmum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
}
