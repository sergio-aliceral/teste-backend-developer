package com.teste.application.documentation.update;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teste.application.documentation.update.request.DocumentationUpdateRequest;
import com.teste.application.documentation.update.response.DocumentationUpdateResponse;
import com.teste.domain.User;
import com.teste.domain.UserDocumentation;
import com.teste.infrastructure.exception.BusinessException;
import com.teste.infrastructure.repository.DocumentationRepository;
import com.teste.infrastructure.repository.UserRepository;

@Service
public class DocumentationUpdateService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DocumentationRepository documentationRepository;

	public DocumentationUpdateResponse update(DocumentationUpdateRequest documentationUpdateRequest) {
		Optional<UserDocumentation> optional = documentationRepository.findById(documentationUpdateRequest.getId());

		if (optional.isEmpty()) {
			throw new BusinessException("Documento não existe");
		}

		if (!isUser(documentationUpdateRequest)) {
			throw new BusinessException("Usuário não existe");
		}

		UserDocumentation newUserDocumentation = buildUserDocumentation(documentationUpdateRequest);
		UserDocumentation userDocumentation = documentationRepository.save(newUserDocumentation);
		return buildDocumentationUpdateResponse(userDocumentation);
	}

	private boolean isUser(DocumentationUpdateRequest documentationUpdateRequest) {
		return userRepository.findById(documentationUpdateRequest.getIdUser()).isPresent();
	}

	private UserDocumentation buildUserDocumentation(DocumentationUpdateRequest documentationUpdateRequest) {

		try {

			MultipartFile multipartFile = documentationUpdateRequest.getDocument();
			byte[] bytes = multipartFile.getBytes();
			User user = new User(documentationUpdateRequest.getIdUser());
			return new UserDocumentation(bytes, documentationUpdateRequest.getTipoDocumento(), user);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private DocumentationUpdateResponse buildDocumentationUpdateResponse(UserDocumentation userDocumentation) {
		byte[] document = userDocumentation.getDocument();
		User user = userDocumentation.getUser();
		return new DocumentationUpdateResponse(userDocumentation.getId(), document, userDocumentation.getTipoDocumento(), user.getId());
	}

}
