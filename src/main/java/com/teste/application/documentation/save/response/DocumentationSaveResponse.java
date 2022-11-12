package com.teste.application.documentation.save.response;

import java.io.Serializable;

import com.teste.emums.TipoDocumentoEmum;

public class DocumentationSaveResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private byte [] document;
	
	private TipoDocumentoEmum tipoDocumento;

	private Long idUser;
	
	public DocumentationSaveResponse() { }
	
	public DocumentationSaveResponse(Long id, byte [] document, TipoDocumentoEmum tipoDocumento, Long idUser) {
		this.id = id;
		this.document = document;
		this.tipoDocumento = tipoDocumento;
		this.idUser = idUser;
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

	public Long getIdUser() {
		return idUser;
	}

}
