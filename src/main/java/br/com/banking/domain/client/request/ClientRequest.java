package br.com.banking.domain.client.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Descreve o objeto de requisição do cliente")
public class ClientRequest {

    @ApiModelProperty(value = "Nome do cliente", example = "João da Silva", position = 1)

    private String nome;

    @ApiModelProperty(value = "Numero do CPF", example = "123.456.789-12", position = 2)
    private String cpf;

    @ApiModelProperty(value = "Comprovante de Residência", example = "Rua das Flores, 123", position = 3)
    private String comprovanteResidencia;
}
