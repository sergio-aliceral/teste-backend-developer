package com.teste.application.user.delete;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.domain.User;
import com.teste.infrastructure.exception.NotFoundException;
import com.teste.infrastructure.repository.UserRepository;

@Service
public class UserDeleteService {

	@Autowired
	private UserRepository userRepository;

	public void delete(Long id) {

		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {
			throw new NotFoundException("Usuário não existe");
		}

		userRepository.delete(optional.get());
	}

}
