package fr.andromeda.cyb.controllers;

import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.enums.Urls;
import fr.andromeda.cyb.services.interfaces.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}/roles")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody RoleDTO roleDTO) throws ResourceNotFoundException {
        RoleDTO saved = roleService.create(roleDTO);
        logger.debug("role {} created", saved.getId());
        URI location = ServletUriComponentsBuilder.fromPath("/api/v1/roles")
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> createRoles(@RequestBody List<RoleDTO> roleDTOList) {
        roleService.createAll(roleDTOList);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(Urls.PATH_ID)
    public ResponseEntity<RoleDTO> getRole(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(roleService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @PatchMapping(Urls.PATH_ID )
    public ResponseEntity<Void> patchRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO) throws ResourceNotFoundException {
        roleService.patch(id, roleDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(Urls.PATH_ID )
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) throws ResourceNotFoundException {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
