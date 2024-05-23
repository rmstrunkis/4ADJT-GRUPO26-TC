package br.com.banking.domain.client.controller;

import br.com.banking.domain.client.Client;
import br.com.banking.domain.client.request.ClientRequest;
import br.com.banking.domain.client.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@Api(value = "Client Management", description = "Operations pertaining to client management")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Create a new client")
    @PostMapping("/create")
    public ResponseEntity<Client> post(@RequestBody ClientRequest request) {
        return clientService.criaClienteSeNaoExistir(request);
    }
}
