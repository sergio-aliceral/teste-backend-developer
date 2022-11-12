package com.teste.application.documentation.get;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teste.application.documentation.get.response.DocumentationGetResponse;
import com.teste.domain.User;
import com.teste.domain.UserDocumentation;
import com.teste.infrastructure.repository.DocumentationRepository;

@Service
public class DocumentationGetService {

	@Autowired
	private DocumentationRepository documentationRepository;

	public Page<DocumentationGetResponse> get(Pageable pageable) {
		Page<UserDocumentation> page = documentationRepository.findAll(pageable);
		List<DocumentationGetResponse> documentations = page.stream().map(doc -> buildDocumentationGetResponse(doc)).collect(toList());
		return new PageImpl<>(documentations, pageable, page.getTotalElements());
	}

	private DocumentationGetResponse buildDocumentationGetResponse(UserDocumentation userDocumentation) {
		byte[] document = userDocumentation.getDocument();
		User user = userDocumentation.getUser();
		return new DocumentationGetResponse(userDocumentation.getId(), document, userDocumentation.getTipoDocumento(), user.getId());
	}

}
