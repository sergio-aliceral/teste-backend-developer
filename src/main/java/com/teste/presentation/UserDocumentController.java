package com.teste.presentation;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teste.application.documentation.delete.DocumentationDeleteService;
import com.teste.application.documentation.get.DocumentationGetService;
import com.teste.application.documentation.get.response.DocumentationGetResponse;
import com.teste.application.documentation.save.DocumentationSaveService;
import com.teste.application.documentation.save.request.DocumentationSaveRequest;
import com.teste.application.documentation.save.response.DocumentationSaveResponse;
import com.teste.application.documentation.update.DocumentationUpdateService;
import com.teste.application.documentation.update.request.DocumentationUpdateRequest;
import com.teste.application.documentation.update.response.DocumentationUpdateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user-documentation")
@Tag(name = "User Documentation")
public class UserDocumentController {

	@Autowired
	private DocumentationSaveService documentationSaveService;
	@Autowired
	private DocumentationUpdateService documentationUpdateService;
	@Autowired
	private DocumentationGetService documentationGetService;
	@Autowired
	private DocumentationDeleteService documentationDeleteService;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Save User Documentation")
	@PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
	public DocumentationSaveResponse save(@Valid DocumentationSaveRequest documentationSaveRequest) {
		return documentationSaveService.save(documentationSaveRequest);
	}

	
	@PutMapping("/{id}")
	@Operation(summary = "Update User Documentation")
	public DocumentationUpdateResponse update(@PathVariable Long id, @Valid DocumentationUpdateRequest documentationUpdateRequest) {
		documentationUpdateRequest.setId(id);
		return documentationUpdateService.update(documentationUpdateRequest);
	}

	@GetMapping
	@Operation(summary = "Get User Documentation")
	public Page<DocumentationGetResponse> get(Pageable pageable) {
		return documentationGetService.get(pageable);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete User Documentation")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		documentationDeleteService.delete(id);
	}

}
