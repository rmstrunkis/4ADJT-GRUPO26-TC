# Resume: Get list account 
description: This resource return a list of exists accounts

**Path:** GET /accounts/list

**Response**
br.com.banking.domain.response.dto.AccountResponseDto

**External Dependencies**

	* h2
	
**Execution flow**

1. Validate if the list is empty
2. Return a list of exists accounts

## Cases of test

* success: 200
* no content: 204
* server error: 500

