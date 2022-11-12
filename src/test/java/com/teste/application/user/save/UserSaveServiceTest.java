package com.teste.application.user.save;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.teste.application.user.save.request.UserSaveRequest;
import com.teste.application.user.save.response.UserSaveResponse;
import com.teste.domain.User;
import com.teste.infrastructure.exception.BusinessException;
import com.teste.infrastructure.repository.UserRepository;

class UserSaveServiceTest extends TesteBackendDeveloperApplicationTests {

	@InjectMocks
	private UserSaveService userSaveService;

	@Mock
	private UserRepository userRepository;

	@Test
	void saveTest() {

		UserSaveRequest userSaveRequestMock = buildUserSaveRequest();
		User userSavedMock = buildUser();
		when(userRepository.findByCpf(userSaveRequestMock.getCpf())).thenReturn(Optional.empty());
		when(userRepository.save(any())).thenReturn(userSavedMock);

		UserSaveResponse userSaveResponse = userSaveService.save(userSaveRequestMock);

		assertNotNull(userSaveResponse.getId());
		assertEquals(userSaveRequestMock.getNome(), userSaveResponse.getNome());
		assertEquals(userSaveRequestMock.getCpf(), userSaveResponse.getCpf());
		verify(userRepository).findByCpf(userSaveRequestMock.getCpf());
		verify(userRepository).save(any());
		verifyNoMoreInteractions(userRepository);
	}

	@Test
	void saveUsuarioCadastradoTest() {

		UserSaveRequest userSaveRequestMock = buildUserSaveRequest();
		User userSavedMock = buildUser();
		Optional<User> optional = Optional.of(userSavedMock);
		when(userRepository.findByCpf(userSaveRequestMock.getCpf())).thenReturn(optional);

		assertThrows(BusinessException.class, () -> userSaveService.save(userSaveRequestMock));

		verify(userRepository).findByCpf(userSaveRequestMock.getCpf());
		verifyNoMoreInteractions(userRepository);
	}

	private UserSaveRequest buildUserSaveRequest() {
		return new UserSaveRequest("Nome", "12312312387");
	}

	private User buildUser() {
		return new User(1L, "Nome", "12312312387");
	}

}
