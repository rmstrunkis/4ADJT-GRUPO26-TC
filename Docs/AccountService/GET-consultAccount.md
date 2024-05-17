# Resume: Get account 
description: This resource return an exist account

**Path:** GET /accounts/consult

**Response**
br.com.banking.domain.response.dto.AccountResponseDto

**External Dependencies**

	* h2
	
**Execution flow**

1. Validate if account exists by `id`
2. return updated object data

## Cases of test

* success: 200
* not found: 404
* server error: 500

