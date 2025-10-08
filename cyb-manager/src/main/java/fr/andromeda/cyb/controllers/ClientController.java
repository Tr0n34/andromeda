package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.dto.ClientDTO;
import fr.andromeda.cyb.enums.Urls;
import fr.andromeda.cyb.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "${api.prefix}/clients")
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

    @GetMapping(Urls.PATH_ID )
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO client) {
        return null;
    }

    @PutMapping(Urls.PATH_ID )
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO updatedClient) {
        return null;
    }

    @DeleteMapping(Urls.PATH_ID )
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

}
