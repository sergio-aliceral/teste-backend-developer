package com.teste.application.user.update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.application.user.update.request.UserUpdateRequest;
import com.teste.application.user.update.response.UserUpdateResponse;
import com.teste.domain.User;
import com.teste.infrastructure.exception.BusinessException;
import com.teste.infrastructure.exception.NotFoundException;
import com.teste.infrastructure.repository.UserRepository;

@Service
public class UserUpdateService {

	@Autowired
	private UserRepository userRepository;

	public UserUpdateResponse update(UserUpdateRequest userUpdateRequest) {
		Optional<User> optional = userRepository.findById(userUpdateRequest.getId());

		if (optional.isEmpty()) {
			throw new NotFoundException("Usuário não existe");
		}

		if (isUserExist(userUpdateRequest)) {
			throw new BusinessException("CPF já está em uso");
		}

		User user = optional.get();
		user.setNome(userUpdateRequest.getNome());
		user.setCpf(userUpdateRequest.getCpf());
		User userSaved = userRepository.save(user);
		return buildUserUpdateResponse(userSaved);
	}

	private boolean isUserExist(UserUpdateRequest userUpdateRequest) {
		return userRepository.findByCpfAndIdNot(userUpdateRequest.getCpf(), userUpdateRequest.getId()).isPresent();
	}

	private UserUpdateResponse buildUserUpdateResponse(User user) {
		return new UserUpdateResponse(user.getId(), user.getNome(), user.getCpf());
	}

}
