package br.com.banking.domain.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Describe an account request update object")
public class AccountRequestUpdate {
	
	@ApiModelProperty(value = "Account information for active or not", example = "true", position = 1)
	private Boolean active;
}