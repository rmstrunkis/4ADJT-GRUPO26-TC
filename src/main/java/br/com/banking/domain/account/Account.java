package br.com.banking.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idConta;

	@Column(name = "CONTAPOUPANCA")
	private Long contaPoupanca;

	@Column(name = "AGENCIA")
	private Long agencia;

	@Column(name = "FLAGATIVA")
	private Boolean flag;
}