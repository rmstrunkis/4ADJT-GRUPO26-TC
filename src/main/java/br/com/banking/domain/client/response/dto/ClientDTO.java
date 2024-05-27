package br.com.banking.domain.client.response.dto;

import br.com.banking.domain.client.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "Descreve o objeto de requisição do cliente")
public class ClientDTO {

    public ClientDTO(Client client) {
        this.id = client.getIdCliente();
        this.nome = client.getNome();
        this.cpf = client.getCpf();
    }

    @ApiModelProperty(value = "ID do cliente", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "Nome do cliente", example = "João da Silva", position = 1)
    private String nome;

    @ApiModelProperty(value = "Numero do CPF", example = "123.456.789-12", position = 1)
    private String cpf;
}