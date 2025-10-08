package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.dto.ApplicationParameterDTO;
import fr.andromeda.cyb.enums.AppParameter;
import fr.andromeda.cyb.enums.Urls;
import fr.andromeda.cyb.services.impl.ApplicationParameterService;
import fr.andromeda.cyb.services.interfaces.IApplicationParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/parameters")
public class ApplicationParameterController {

    private final IApplicationParameterService applicationParameterService;

    @Autowired
    public ApplicationParameterController(ApplicationParameterService applicationParameterService) {
        this.applicationParameterService = applicationParameterService;
    }

    @GetMapping(Urls.PATH_ID)
    public ResponseEntity<ApplicationParameterDTO> getApplicationParameter(@PathVariable("id") Long id) {
        return ResponseEntity.ok(applicationParameterService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationParameterDTO>> getAllApplicationParameters() {
        return ResponseEntity.ok(applicationParameterService.findAll());
    }

    @PatchMapping(Urls.PATH_ID)
    public ResponseEntity<ApplicationParameterDTO> updateValue(@PathVariable("id") Long id, @RequestBody ApplicationParameterDTO applicationParameterDTO) {
        applicationParameterService.patch(id, applicationParameterDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/key/{key}")
    public ResponseEntity<ApplicationParameterDTO> updateValue(@PathVariable("key") AppParameter key, @RequestBody ApplicationParameterDTO applicationParameterDTO) {

        return ResponseEntity.noContent().build();
    }


}
