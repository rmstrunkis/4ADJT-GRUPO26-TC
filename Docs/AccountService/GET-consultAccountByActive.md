# Resume: Get list account by active
description: This resource return a list of exists accounts by active

**Path:** GET /accounts/active

**Response**
br.com.banking.domain.response.dto.AccountResponseDto

**External Dependencies**

	* h2
	
**Execution flow**

1. Validate if the list is empty by active
2. Return a list of exists accounts active

## Cases of test

* success: 200
* no content: 204
* server error: 500

