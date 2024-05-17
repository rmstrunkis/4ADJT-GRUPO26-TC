# Resume: Update account to active or non active
description: This resource update an account to active or not

**Path:** PUT /accounts/update/{id}

**Request**
br.com.banking.domain.request.AccountRequest

**Response**
br.com.banking.domain.response.dto.AccountResponseDto

**External Dependencies**

	* h2
	
**Execution flow**

1. Validate if account exists by `id`
2. Verify if data to update is the same data already exists in database
3. Update account to active or not active that matches `id`
4. return updated object data

## Cases of test

* success: 200
* bad request: 400
* not found: 404
* server error: 500
