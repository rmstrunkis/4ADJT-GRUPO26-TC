# Resume: Create account 
description: This resource creates an account

**Path:** POST /accounts/create

**Request**
br.com.banking.domain.request.AccountRequest

**Response**
br.com.banking.domain.response.dto.AccountResponseDto

**External Dependencies**

	* h2
	
**Execution flow**

1. Validate if requisition is empty
2. Validate if documentId already exists
3. Create account
4. return updated object data

## Cases of test

* success: 200
* bad request: 400
* server error: 500
