package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "${api.prefix}/errors")
public class ErrorController {

    private final ErrorService errorService;

    @Autowired
    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @PostMapping
    public ResponseEntity<Void> createError(@RequestBody ErrorDTO errorDTO) {
        Long id = errorService.add(errorDTO);
        URI location = ServletUriComponentsBuilder.fromPath("/api/v1/errors")
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }



}
