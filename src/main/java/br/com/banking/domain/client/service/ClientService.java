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

}
