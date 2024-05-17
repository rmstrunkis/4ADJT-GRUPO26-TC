package br.com.banking.domain.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banking.domain.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	List<Account> findByActive(Boolean status);	
	
	Optional<Account> findById(Long id);
	
	Optional<Account> findByDocumentId(String document);
	
}
