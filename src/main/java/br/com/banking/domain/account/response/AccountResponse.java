package br.com.banking.domain.account.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "Describe an account response object")
public class AccountResponse {

	@ApiModelProperty(value = "Account ID", example = "1", position = 1)
	private Long id;
}
