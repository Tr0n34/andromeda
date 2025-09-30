package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.services.impl.UserService;
import fr.andromeda.cyb.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws ResourceNotFoundException {
        UserDTO newUserDTO = userService.create(userDTO);
        URI location = ServletUriComponentsBuilder.fromPath("/api/v1/users")
                .path("/{id}")
                .buildAndExpand(newUserDTO.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws ResourceNotFoundException {
        UserDTO userDTO = userService.get(id);
        return ResponseEntity.ok().body(userDTO);
    }


}
