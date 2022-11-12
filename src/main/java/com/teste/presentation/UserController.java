package com.teste.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teste.application.user.delete.UserDeleteService;
import com.teste.application.user.get.UserGetService;
import com.teste.application.user.get.response.UserGetResponse;
import com.teste.application.user.save.UserSaveService;
import com.teste.application.user.save.request.UserSaveRequest;
import com.teste.application.user.save.response.UserSaveResponse;
import com.teste.application.user.update.UserUpdateService;
import com.teste.application.user.update.request.UserUpdateRequest;
import com.teste.application.user.update.response.UserUpdateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {

	@Autowired
	private UserSaveService userSaveService;
	@Autowired
	private UserUpdateService userUpdateService;
	@Autowired
	private UserGetService userGetService;
	@Autowired
	private UserDeleteService userDeleteService;

	@PostMapping
	@Operation(summary = "Save User")
	@ResponseStatus(code = CREATED)
	public UserSaveResponse save(@RequestBody @Valid UserSaveRequest userSaveRequest) {
		return userSaveService.save(userSaveRequest);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update User")
	public UserUpdateResponse update(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
		userUpdateRequest.setId(id);
		return userUpdateService.update(userUpdateRequest);
	}

	@GetMapping
	@Operation(summary = "Get User")
	public Page<UserGetResponse> get(Pageable pageable) {
		return userGetService.get(pageable);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete User")
	@ResponseStatus(code = NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userDeleteService.delete(id);
	}

}
