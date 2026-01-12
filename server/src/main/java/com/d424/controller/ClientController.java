package com.d424.controller;

import com.d424.model.Client;
import com.d424.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String zipcode
    ) {
        if (firstName != null && lastName != null) {
            return clientService.getClientByFirstNameLastName(firstName, lastName);
        } else if (zipcode != null) {
            return clientService.getClientByZipcode(zipcode);
        }
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) {
        return clientService.getClientById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable int id, @RequestBody Client client) {
        client.setClientId(id);
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }
}
