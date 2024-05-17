package br.com.banking.domain.account.response.dto;

import br.com.banking.domain.account.Account;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Describe an account response object")
public class AccountResponseDto {
	
	public AccountResponseDto(Account account) {
		this.id = account.getIdConta();
	}

	@ApiModelProperty(value = "Account ID", example = "1", position = 1)
	private Long id;
	
	@ApiModelProperty(value = "Document number", example = "12345678900")
	private String documentId;
	
	@ApiModelProperty(value = "Account active", example = "true")
	private Boolean active;
}
