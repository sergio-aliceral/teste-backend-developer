package com.teste.application.documentation.delete;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.domain.UserDocumentation;
import com.teste.infrastructure.exception.NotFoundException;
import com.teste.infrastructure.repository.DocumentationRepository;

@Service
public class DocumentationDeleteService {

	@Autowired
	private DocumentationRepository documentationRepository;

	public void delete(Long id) {

		Optional<UserDocumentation> optional = documentationRepository.findById(id);

		if (optional.isEmpty()) {
			throw new NotFoundException("Documento não existe");
		}

		documentationRepository.delete(optional.get());
	}

}
