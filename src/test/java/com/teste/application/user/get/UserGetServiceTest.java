package com.teste.application.user.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.teste.TesteBackendDeveloperApplicationTests;
import com.teste.application.user.get.response.UserGetResponse;
import com.teste.domain.User;
import com.teste.infrastructure.repository.UserRepository;

class UserGetServiceTest extends TesteBackendDeveloperApplicationTests {

	@InjectMocks
	private UserGetService userGetService;

	@Mock
	private UserRepository userRepository;

	@Test
	void getTest() {

		PageRequest pageRequestMock = PageRequest.of(0, 10);
		User userMock = buildUser();
		List<User> listUserMock = Arrays.asList(userMock);
		Page<User> pageMock = new PageImpl<>(listUserMock, pageRequestMock, listUserMock.size());
		when(userRepository.findAll(pageRequestMock)).thenReturn(pageMock);

		Page<UserGetResponse> pageResponse = userGetService.get(pageRequestMock);

		assertNotNull(pageResponse);
		List<UserGetResponse> listUserGetResponse = pageResponse.getContent();
		assertNotNull(listUserGetResponse);
		assertEquals(listUserMock.size(), listUserGetResponse.size());
		UserGetResponse userGetResponse = listUserGetResponse.get(0);
		assertEquals(userMock.getId(), userGetResponse.getId());
		assertEquals(userMock.getNome(), userGetResponse.getNome());
		assertEquals(userMock.getCpf(), userGetResponse.getCpf());
		verify(userRepository).findAll(pageRequestMock);
		verifyNoMoreInteractions(userRepository);
	}

	private User buildUser() {
		return new User(1L, "Nome", "12312312387");
	}

}
