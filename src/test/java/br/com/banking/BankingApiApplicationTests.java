package br.com.banking;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.banking.domain.account.Account;
import br.com.banking.domain.account.request.AccountRequest;
import br.com.banking.domain.account.request.AccountRequestUpdate;
import br.com.banking.domain.account.response.AccountResponse;
import br.com.banking.domain.account.response.dto.AccountResponseDto;
import br.com.banking.domain.account.repository.AccountRepository;
import br.com.banking.domain.account.service.AccountService;

@SpringBootTest
class BankingApiApplicationTests {

	@Mock
	AccountRepository accountRepository;

	@Mock
	AccountService mockAccountService;

	@InjectMocks
	AccountService accountService;

	private Account mockAccount;
	private AccountRequest mockAccountRequest;
	private AccountRequestUpdate mockAccountRequestUpdate;
	private List<Account> mockListAccount;

	@BeforeEach
	public void Setup() {
		mockAccount = new Account();
		mockAccount.setId(1L);
		mockAccount.setDocumentId("12345678900");
		mockAccount.setActive(Boolean.TRUE);

		mockAccountRequest = new AccountRequest();
		mockAccountRequest.setDocument(mockAccount.getDocumentId());
		mockAccountRequest.setActive(mockAccount.getActive());

		mockAccountRequestUpdate = new AccountRequestUpdate();
		mockAccountRequestUpdate.setActive(Boolean.FALSE);

		mockListAccount = new ArrayList<>();
		mockListAccount.add(mockAccount);
	}

	@Test
	void sucessUpdateAccount() {
		when(accountRepository.findById(anyLong())).thenReturn(Optional.of(mockAccount));
		when(accountRepository.save(mockAccount)).thenReturn(mockAccount);

		ResponseEntity<AccountResponseDto> response = accountService.putAccount(mockAccountRequestUpdate, anyLong());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(accountRepository, times(2)).findById(anyLong());
	}

	@Test
	void badRequestUpdateAccount() {
		when(accountRepository.findById(anyLong())).thenReturn(Optional.of(mockAccount));
		when(accountRepository.save(mockAccount)).thenReturn(mockAccount);

		AccountRequestUpdate mockAccountRequestUpdate = new AccountRequestUpdate();
		mockAccountRequestUpdate.setActive(Boolean.TRUE);

		ResponseEntity<AccountResponseDto> response = accountService.putAccount(mockAccountRequestUpdate, anyLong());

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		verify(accountRepository, times(2)).findById(anyLong());
	}

	@Test
	void notFoundtUpdateAccount() {
		when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());
		when(accountRepository.save(mockAccount)).thenReturn(mockAccount);

		ResponseEntity<AccountResponseDto> response = accountService.putAccount(mockAccountRequestUpdate, anyLong());

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(accountRepository, times(1)).findById(anyLong());
	}

	@Test
	void sucessPostAccount() {
		when(accountRepository.findByDocumentId(anyString())).thenReturn(Optional.empty());
		when(accountRepository.save(any(Account.class))).thenReturn(mockAccount);

		ResponseEntity<AccountResponseDto> response = accountService.postAccount(mockAccountRequest);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(accountRepository, times(1)).findByDocumentId(anyString());
	}

	@Test
	void badRequestPostAccount() {
		when(accountRepository.findByDocumentId(anyString())).thenReturn(Optional.of(mockAccount));
		when(accountRepository.save(mockAccount)).thenReturn(mockAccount);

		ResponseEntity<AccountResponseDto> response = accountService.postAccount(mockAccountRequest);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		verify(accountRepository, times(1)).findByDocumentId(anyString());
	}

	@Test
	void sucessDeleteAccount() {
		when(accountRepository.findById(anyLong())).thenReturn(Optional.of(mockAccount));

		ResponseEntity<AccountResponse> response = accountService.deleteAccount(anyLong());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(accountRepository, times(1)).findById(anyLong());
	}

	@Test
	void badRequestDeleteAccount() {
		ResponseEntity<AccountResponse> response = accountService.deleteAccount(null);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	void notFoundDeleteAccount() {
		when(accountRepository.findByDocumentId(anyString())).thenReturn(Optional.empty());

		ResponseEntity<AccountResponse> response = accountService.deleteAccount(anyLong());

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(accountRepository, times(1)).findById(anyLong());
	}

	@Test
	void sucessConsultAccount() {
		when(accountRepository.findById(anyLong())).thenReturn(Optional.of(mockAccount));
		when(accountRepository.save(mockAccount)).thenReturn(mockAccount);

		ResponseEntity<AccountResponseDto> response = accountService.consultAccount(anyLong());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(accountRepository, times(1)).findById(anyLong());
	}

	@Test
	void notFoundConsultAccount() {
		when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

		ResponseEntity<AccountResponseDto> response = accountService.consultAccount(anyLong());

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(accountRepository, times(1)).findById(anyLong());
	}

	@Test
	void sucessConsultAccountByActive() {
		when(accountRepository.findByActive(anyBoolean())).thenReturn(mockListAccount);

		ResponseEntity<List<AccountResponseDto>> response = accountService.listAccountByStatus(anyBoolean());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(accountRepository, times(1)).findByActive(anyBoolean());
	}

	@Test
	void noContentConsultAccountByActive() {
		when(accountRepository.findByActive(null)).thenReturn(mockListAccount);

		ResponseEntity<List<AccountResponseDto>> response = accountService.listAccountByStatus(anyBoolean());

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(accountRepository, times(1)).findByActive(anyBoolean());
	}

	@Test
	void sucessConsultAccountList() {
		when(accountRepository.findAll()).thenReturn(mockListAccount);

		ResponseEntity<List<AccountResponseDto>> response = accountService.listAccount();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(accountRepository, times(1)).findAll();
	}

	@Test
	void noContentConsultAccountList() {
		List<Account> mockListAccount = new ArrayList<>();
		mockListAccount.isEmpty();
		when(accountRepository.findAll()).thenReturn(mockListAccount);

		ResponseEntity<List<AccountResponseDto>> response = accountService.listAccount();

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(accountRepository, times(1)).findAll();
	}
}
