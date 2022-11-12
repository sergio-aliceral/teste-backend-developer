package com.teste.application.documentation.save;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teste.application.documentation.save.request.DocumentationSaveRequest;
import com.teste.application.documentation.save.response.DocumentationSaveResponse;
import com.teste.domain.User;
import com.teste.domain.UserDocumentation;
import com.teste.infrastructure.exception.BusinessException;
import com.teste.infrastructure.repository.DocumentationRepository;
import com.teste.infrastructure.repository.UserRepository;

@Service
public class DocumentationSaveService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DocumentationRepository documentationRepository;

	public DocumentationSaveResponse save(DocumentationSaveRequest documentationSaveRequest) {

		if (!isUser(documentationSaveRequest)) {
			throw new BusinessException("Usuário não existe");
		}

		UserDocumentation newUserDocumentation = buildUserDocumentation(documentationSaveRequest);
		UserDocumentation userDocumentation = documentationRepository.save(newUserDocumentation);
		return buildDocumentationSaveResponse(userDocumentation);
	}

	private boolean isUser(DocumentationSaveRequest documentationSaveRequest) {
		return userRepository.findById(documentationSaveRequest.getIdUser()).isPresent();
	}

	private UserDocumentation buildUserDocumentation(DocumentationSaveRequest documentationSaveRequest) {

		try {

			MultipartFile multipartFile = documentationSaveRequest.getDocument();
			byte[] bytes = multipartFile.getBytes();
			User user = new User(documentationSaveRequest.getIdUser());
			return new UserDocumentation(bytes, documentationSaveRequest.getTipoDocumento(), user);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private DocumentationSaveResponse buildDocumentationSaveResponse(UserDocumentation userDocumentation) {
		byte[] document = userDocumentation.getDocument();
		User user = userDocumentation.getUser();
		return new DocumentationSaveResponse(userDocumentation.getId(), document, userDocumentation.getTipoDocumento(), user.getId());
	}

}
