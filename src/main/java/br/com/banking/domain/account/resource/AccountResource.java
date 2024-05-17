package br.com.banking.domain.account.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banking.domain.account.request.AccountRequest;
import br.com.banking.domain.account.request.AccountRequestUpdate;
import br.com.banking.domain.account.response.AccountResponse;
import br.com.banking.domain.account.response.dto.AccountResponseDto;
import br.com.banking.domain.account.service.AccountService;
import io.swagger.annotations.ApiModel;

@RestController
@RequestMapping(name = "/accounts")
@ApiModel(description = "")
public class AccountResource {

	@Autowired
	private AccountService accountService;
		
	@PostMapping("/create")
	public ResponseEntity<AccountResponseDto> post(@RequestBody AccountRequest request) {
		return accountService.postAccount(request);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<AccountResponse> delete(@RequestParam Long id) {
		return accountService.deleteAccount(id);
	}
	
	@GetMapping("/consult")
	public ResponseEntity<AccountResponseDto> consult(@RequestParam Long id) {
		return accountService.consultAccount(id);
	}

	@GetMapping("/list")
	public ResponseEntity<List<AccountResponseDto>> list() {
		return accountService.listAccount();
	}
	
	@GetMapping("/Active")
	public ResponseEntity<List<AccountResponseDto>> listByStatus(@RequestParam Boolean status) {
		return accountService.listAccountByStatus(status);
	}
	
	@PutMapping("/update" + "/{id}")
	public ResponseEntity<AccountResponseDto> update(@RequestBody AccountRequestUpdate request, @RequestParam Long id) {
		return accountService.putAccount(request, id);
	}
}