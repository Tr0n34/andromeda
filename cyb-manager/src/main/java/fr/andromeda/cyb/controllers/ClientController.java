package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.dto.ClientDTO;
import fr.andromeda.cyb.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {

    private IClientService IClientService;

    @Autowired
    public ClientController(IClientService IClientService) {
        this.IClientService = IClientService;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO client) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO updatedClient) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

}
