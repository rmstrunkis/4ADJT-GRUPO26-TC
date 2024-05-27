package br.com.banking.domain.client.controller;

import br.com.banking.domain.client.Client;
import br.com.banking.domain.client.request.ClientRequest;
import br.com.banking.domain.client.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "Update client details")
    @PutMapping("/update/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody ClientRequest request) {
        return clientService.atualizaCliente(id, request);
    }

    @ApiOperation(value = "Delete a client")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return clientService.excluiCliente(id);
    }

    @ApiOperation(value = "Get a client by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClienteById(id);
    }

    @ApiOperation(value = "Get all clients")
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return clientService.getAllClients();
    }
}