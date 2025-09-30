package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.services.impl.RoleService;
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

    private RoleService roleService;

    public RoleController(RoleService roleService) {
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

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateError(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO) throws ResourceNotFoundException {
        roleService.patch(id, roleDTO);
        return ResponseEntity.noContent().build();
    }


}
