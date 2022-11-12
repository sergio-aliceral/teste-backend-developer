package com.teste.application.user.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.teste.application.user.update.request.UserUpdateRequest;
import com.teste.application.user.update.response.UserUpdateResponse;
import com.teste.domain.User;
import com.teste.infrastructure.exception.BusinessException;
import com.teste.infrastructure.exception.NotFoundException;
import com.teste.infrastructure.repository.UserRepository;

class UserUpdateServiceTest extends TesteBackendDeveloperApplicationTests {

	@InjectMocks
	private UserUpdateService userUpdateService;

	@Mock
	private UserRepository userRepository;

	@Test
	void updateTest() {

		UserUpdateRequest userUpdateRequestMock = buildUserUpdateRequest();
		User userMock = buildUser();
		when(userRepository.findById(userUpdateRequestMock.getId())).thenReturn(Optional.of(userMock));
		when(userRepository.findByCpfAndIdNot(userUpdateRequestMock.getCpf(), userUpdateRequestMock.getId())).thenReturn(Optional.empty());
		when(userRepository.save(any())).thenReturn(userMock);

		UserUpdateResponse userUpdateResponse = userUpdateService.update(userUpdateRequestMock);

		assertEquals(userUpdateRequestMock.getId(), userUpdateResponse.getId());
		assertEquals(userUpdateRequestMock.getNome(), userUpdateResponse.getNome());
		assertEquals(userUpdateRequestMock.getCpf(), userUpdateResponse.getCpf());
		verify(userRepository).findById(userUpdateRequestMock.getId());
		verify(userRepository).findByCpfAndIdNot(userUpdateRequestMock.getCpf(), userUpdateRequestMock.getId());
		verify(userRepository).save(any());
		verifyNoMoreInteractions(userRepository);
	}
	
	@Test
	void updateNotFoundTest() {

		UserUpdateRequest userUpdateRequestMock = buildUserUpdateRequest();
		when(userRepository.findById(userUpdateRequestMock.getId())).thenReturn(Optional.empty());
		
		assertThrows(NotFoundException.class, () -> userUpdateService.update(userUpdateRequestMock));
		
		verify(userRepository).findById(userUpdateRequestMock.getId());
		verifyNoMoreInteractions(userRepository);
	}

	@Test
	void updateCpfEmUsoTest() {

		UserUpdateRequest userUpdateRequestMock = buildUserUpdateRequest();
		User userMock = buildUser();
		when(userRepository.findById(userUpdateRequestMock.getId())).thenReturn(Optional.of(userMock));
		when(userRepository.findByCpfAndIdNot(userUpdateRequestMock.getCpf(), userUpdateRequestMock.getId())).thenReturn(Optional.of(userMock));
		
		assertThrows(BusinessException.class, () -> userUpdateService.update(userUpdateRequestMock));
		
		verify(userRepository).findById(userUpdateRequestMock.getId());
		verify(userRepository).findByCpfAndIdNot(userUpdateRequestMock.getCpf(), userUpdateRequestMock.getId());
		verifyNoMoreInteractions(userRepository);
	}
	
	private UserUpdateRequest buildUserUpdateRequest() {
		UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
		userUpdateRequest.setId(1L);
		userUpdateRequest.setNome("Nome");
		userUpdateRequest.setCpf("12312312387");
		return userUpdateRequest;
	}

	private User buildUser() {
		return new User(1L, "Nome", "12312312387");
	}

}
