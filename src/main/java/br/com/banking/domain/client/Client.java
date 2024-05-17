package br.com.banking.domain.client;

import br.com.banking.domain.account.Account;
import br.com.banking.domain.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long idCliente;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "ID")
    private Account conta;

    @OneToOne
    @JoinColumn(name = "ID")
    private Address endereco;
}
