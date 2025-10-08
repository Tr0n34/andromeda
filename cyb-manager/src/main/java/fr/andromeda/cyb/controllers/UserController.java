package fr.andromeda.cyb.controllers;

import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.enums.UrlPattern;
import fr.andromeda.cyb.enums.Urls;
import fr.andromeda.cyb.services.impl.UserService;
import fr.andromeda.cyb.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {

    private final IUserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws ResourceNotFoundException {
        UserDTO newUserDTO = userService.create(userDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(UrlPattern.ID.getPath())
                .buildAndExpand(newUserDTO.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = Urls.PATH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) throws ResourceNotFoundException {
        UserDTO userDTO = userService.get(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PatchMapping(path = Urls.PATH_ID + "/lock")
    public ResponseEntity<?> lockUser(@PathVariable Long id) throws ResourceNotFoundException {
        userService.lock(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = Urls.PATH_ID + "/unlock")
    public ResponseEntity<?> unlockUser(@PathVariable Long id) throws ResourceNotFoundException {
        userService.unlock(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = Urls.PATH_ID)
    public ResponseEntity<?> patchUser(@PathVariable Long id, @RequestBody UserDTO userDTO) throws ResourceNotFoundException {
        userService.patch(id, userDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = Urls.PATH_ID)
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
