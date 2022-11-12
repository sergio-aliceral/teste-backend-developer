package com.teste.application.user.get;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teste.application.user.get.response.UserGetResponse;
import com.teste.domain.User;
import com.teste.infrastructure.repository.UserRepository;

@Service
public class UserGetService {

	@Autowired
	private UserRepository userRepository;

	public Page<UserGetResponse> get(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		List<UserGetResponse> users = page.stream().map(user -> buildUserGetResponse(user)).collect(toList());
		return new PageImpl<>(users, pageable, page.getTotalElements());
	}

	private UserGetResponse buildUserGetResponse(User user) {
		return new UserGetResponse(user.getId(), user.getNome(), user.getCpf());
	}

}
