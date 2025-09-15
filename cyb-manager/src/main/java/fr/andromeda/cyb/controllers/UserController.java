package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.mappers.UserMapper;
import fr.andromeda.cyb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        UserDTO newUserDTO = userService.create(userDTO);
        URI location = ServletUriComponentsBuilder.fromPath("/api/v1/users")
                .path("/{id}")
                .buildAndExpand(newUserDTO.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


}
