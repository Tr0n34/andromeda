package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.enums.UrlPattern;
import fr.andromeda.cyb.enums.Urls;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.services.impl.ErrorService;
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

    private final ErrorService errorService;

    @Autowired
    public ErrorController(ErrorService errorService) {
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
