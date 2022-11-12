package com.teste.application.documentation.save.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.teste.emums.TipoDocumentoEmum;

public class DocumentationSaveRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private MultipartFile document;

	@NotNull
	private TipoDocumentoEmum tipoDocumento;

	@NotNull
	private Long idUser;

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
