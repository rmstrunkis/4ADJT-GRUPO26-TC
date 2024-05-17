# Resume: Delete account 
description: This resource delete an account

**Path:** DELETE /accounts/delete

**Response**
br.com.banking.domain.response.dto.AccountResponse

**External Dependencies**

	* h2
	
**Execution flow**

1. Validate if `id` is empty
2. Validate if exists account by `id`
3. Delete account by `id`
4. return response entity

## Cases of test

* success: 200
* bad request: 400
* not found: 404
* server error: 500
