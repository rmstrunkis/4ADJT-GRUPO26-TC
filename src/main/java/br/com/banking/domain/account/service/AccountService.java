package br.com.banking.domain.account.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.banking.domain.account.Account;
import br.com.banking.domain.account.request.AccountRequest;
import br.com.banking.domain.account.request.AccountRequestUpdate;
import br.com.banking.domain.account.response.AccountResponse;
import br.com.banking.domain.account.response.dto.AccountResponseDto;
import br.com.banking.domain.account.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public ResponseEntity<AccountResponseDto> postAccount(AccountRequest request) {
		if (!ObjectUtils.isEmpty(request)) {
			boolean alredyExists = accountAlreadyExist(request);
			if (Boolean.TRUE.equals(alredyExists)) {
				Account account = new Account();
				Account accountSaved = accountRepository.save(account);
	
				return ResponseEntity.ok(new AccountResponseDto(accountSaved));
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}

	public ResponseEntity<AccountResponse> deleteAccount(Long id) {
		if (!ObjectUtils.isEmpty(id)) {
			Optional<Account> foundedAccount = accountRepository.findById(id);
			if (foundedAccount.isPresent()) {
				accountRepository.deleteById(id);

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	public ResponseEntity<AccountResponseDto> consultAccount(Long id) {
		Optional<Account> foundedAccount = accountRepository.findById(id);
		if (foundedAccount.isPresent()) {			
			Account account = new Account();
			account.setIdConta(foundedAccount.get().getIdConta());
			Account accountSaved = accountRepository.save(account);			
			
			return ResponseEntity.ok(new AccountResponseDto(accountSaved));
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<List<AccountResponseDto>> listAccount() {
		List<Account> findAll = accountRepository.findAll();
		if (!findAll.isEmpty()) {
			List<AccountResponseDto> listResponse = findAll.stream().map(AccountResponseDto::new).collect(Collectors.toList());
		
			return ResponseEntity.ok(listResponse);
		}
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<List<AccountResponseDto>> listAccountByStatus(Boolean status) {
		List<Account> listStatus = accountRepository.findByActive(status);
		if (!listStatus.isEmpty()) {
			List<AccountResponseDto> listResponse = listStatus.stream().map(AccountResponseDto::new).collect(Collectors.toList());
			
			return ResponseEntity.ok(listResponse);
		}
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<AccountResponseDto> putAccount(AccountRequestUpdate request, Long id) {
		Optional<Account> updateAccount = accountRepository.findById(id);
		if (updateAccount.isPresent()) {
			boolean check = checkUpdate(id, request.getActive());
			if (Boolean.TRUE.equals(check)){
				
				Account accountSaved = accountRepository.save(updateAccount.get());
				
				return ResponseEntity.ok(new AccountResponseDto(accountSaved));
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	public Boolean checkUpdate (Long id, Boolean status) {
		Optional<Account> foundedAccount = accountRepository.findById(id);

		return Boolean.TRUE;	
	}
	
	public Boolean accountAlreadyExist(AccountRequest request) {
		Optional<Account> foundedAccount = accountRepository.findByDocumentId(request.getDocument());
		if (foundedAccount.isPresent()) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;	
	}
}
	
