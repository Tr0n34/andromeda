package fr.andromeda.api.controllers;

import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.enums.UrlPattern;
import fr.andromeda.api.enums.Urls;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.services.interfaces.IErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}/errors")
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class.getName());

    private final IErrorService errorService;

    @Autowired
    public ErrorController(IErrorService errorService) {
        this.errorService = errorService;
    }

    @PostMapping
    public ResponseEntity<Void> createError(@RequestBody ErrorDTO errorDTO) throws ResourceNotFoundException {
        ErrorDTO saved = errorService.create(errorDTO);
        logger.debug("error {} created", saved.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(UrlPattern.ID.getPath())
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> createErrors(@RequestBody List<ErrorDTO> errorDTOList) {
        errorService.createAll(errorDTOList);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ErrorDTO>> getAllErrors() {
        return ResponseEntity.ok(errorService.findAll());
    }

    @GetMapping(Urls.PATH_ID)
    public ResponseEntity<ErrorDTO> getError(@PathVariable Long id) {
        return ResponseEntity.ok(errorService.get(id));
    }

    @PatchMapping(path = Urls.PATH_ID)
    public ResponseEntity<Void> updateError(@PathVariable("id") Long id, @RequestBody ErrorDTO errorDTO) throws ResourceNotFoundException {
        errorService.update(id, errorDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = Urls.PATH_ID)
    public ResponseEntity<Void> deleteError(@PathVariable("id") Long id) throws ResourceNotFoundException {
        errorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
