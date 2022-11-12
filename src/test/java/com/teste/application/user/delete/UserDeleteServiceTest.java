package com.teste.application.user.delete;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teste.TesteBackendDeveloperApplicationTests;
import com.teste.domain.User;
import com.teste.infrastructure.exception.NotFoundException;
import com.teste.infrastructure.repository.UserRepository;

class UserDeleteServiceTest extends TesteBackendDeveloperApplicationTests {

	@InjectMocks
	private UserDeleteService userDeleteService;

	@Mock
	private UserRepository userRepository;

	@Test
	void getTest() {

		Long id = 1L;
		User userMock = buildUser();
		when(userRepository.findById(id)).thenReturn(Optional.of(userMock));

		userDeleteService.delete(id);

		verify(userRepository).findById(id);
		verify(userRepository).delete(any());
		verifyNoMoreInteractions(userRepository);
	}

	@Test
	void deleteNotFoundTest() {

		Long id = 1L;
		when(userRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> userDeleteService.delete(id));

		verify(userRepository).findById(id);
		verifyNoMoreInteractions(userRepository);
	}

	private User buildUser() {
		return new User(1L, "Nome", "12312312387");
	}

}
