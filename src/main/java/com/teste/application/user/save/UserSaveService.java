package com.teste.application.user.save;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.application.user.save.request.UserSaveRequest;
import com.teste.application.user.save.response.UserSaveResponse;
import com.teste.domain.User;
import com.teste.infrastructure.exception.BusinessException;
import com.teste.infrastructure.repository.UserRepository;

@Service
public class UserSaveService {

	@Autowired
	private UserRepository userRepository;

	public UserSaveResponse save(UserSaveRequest userSaveRequest) {

		if (isUserExist(userSaveRequest)) {
			throw new BusinessException("Usuário já foi cadastrado");
		}

		User newUser = buildUser(userSaveRequest);
		User user = userRepository.save(newUser);
		return buildUserSaveResponse(user);
	}

	private boolean isUserExist(UserSaveRequest userSaveRequest) {
		return userRepository.findByCpf(userSaveRequest.getCpf()).isPresent();
	}

	private User buildUser(UserSaveRequest userSaveRequest) {
		return new User(userSaveRequest.getNome(), userSaveRequest.getCpf());
	}

	private UserSaveResponse buildUserSaveResponse(User user) {
		return new UserSaveResponse(user.getId(), user.getNome(), user.getCpf());
	}
}
