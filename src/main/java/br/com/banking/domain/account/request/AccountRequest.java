package br.com.banking.domain.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Describe an account request object")
public class AccountRequest {

	@ApiModelProperty(value = "Document identification for account responsible", example = "12345678912", position = 1)
	private String document;
	
	@ApiModelProperty(value = "Account information for active or not", example = "true", position = 1)
	private Boolean active;
}