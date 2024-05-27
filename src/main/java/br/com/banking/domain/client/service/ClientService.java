package br.com.banking.domain.client.service;

import br.com.banking.domain.client.Client;
import br.com.banking.domain.client.repository.ClientRepository;
import br.com.banking.domain.client.request.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<Client> criaClienteSeNaoExistir(ClientRequest clientRequest){
        Optional<Client> clienteEncontrado = clientRepository.findByCpf(clientRequest.getCpf());

        if (!clienteEncontrado.isPresent()){
            Client novoCliente = new Client();
            novoCliente.setNome(clientRequest.getNome());
            novoCliente.setCpf(clientRequest.getCpf());
            novoCliente.setComprovanteResidencia(clientRequest.getComprovanteResidencia());

            Client clienteSalvo = clientRepository.save(novoCliente);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(clienteSalvo.getIdCliente())
                    .toUri();

            return ResponseEntity.created(location).body(clienteSalvo);
        }
        else {
            return ResponseEntity.ok(clienteEncontrado.get());
        }
    }
    public ResponseEntity<Client> atualizaCliente(Long id, ClientRequest clientRequest) {
        Optional<Client> clienteEncontrado = clientRepository.findById(id);
        if (clienteEncontrado.isPresent()) {
            Client cliente = clienteEncontrado.get();
            cliente.setNome(clientRequest.getNome());
            cliente.setComprovanteResidencia(clientRequest.getComprovanteResidencia());
            clientRepository.save(cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> excluiCliente(Long id) {
        Optional<Client> clienteEncontrado = clientRepository.findById(id);
        if (clienteEncontrado.isPresent()) {
            clientRepository.delete(clienteEncontrado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Client> getClienteById(Long id) {
        Optional<Client> clienteEncontrado = clientRepository.findById(id);
        return clienteEncontrado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }
}
