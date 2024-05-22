package br.com.banking.domain.client.controller;

import br.com.banking.domain.client.Client;
import br.com.banking.domain.client.request.ClientRequest;
import br.com.banking.domain.client.service.ClientService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/clients")
@ApiModel(description = "")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Client> post(@RequestBody ClientRequest request) {
        return clientService.criaClienteSeNaoExistir(request);
    }

}
