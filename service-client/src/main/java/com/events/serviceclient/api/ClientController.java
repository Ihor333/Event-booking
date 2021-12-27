package com.events.serviceclient.api;

import com.events.serviceclient.model.Client;
import com.events.serviceclient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {
    private final ClientService client;

    @Autowired
    public ClientController(ClientService client){this.client = client;}

    @GetMapping(value = "/client")
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(client.getAllClients());
    }

    @PostMapping(value = "/client")
    public ResponseEntity<Client> postClient(@Valid @RequestBody Client newClient) {
        return ResponseEntity.ok(client.addNewClient(newClient));
    }

    @GetMapping(value = "/client/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        return ResponseEntity.ok(client.getClientById(clientId));
    }

    @PutMapping(value = "/client/{clientId}")
    public ResponseEntity<Client> updateClientById(@PathVariable Long clientId, @Valid @RequestBody Client updatedClientInfo) {
        return ResponseEntity.ok(client.updateClientById(clientId, updatedClientInfo));
    }
    @DeleteMapping(value = "/client/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(client.deleteClient(clientId));
    }
}
