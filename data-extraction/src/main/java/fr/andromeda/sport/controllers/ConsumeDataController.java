package fr.andromeda.sport.controllers;


import fr.andromeda.sport.objects.dto.RawDataDTO;
import fr.andromeda.sport.services.RawDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}")
public class ConsumeDataController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumeDataController.class);

    private final RawDataService rawDataService;

    @Autowired
    public ConsumeDataController(RawDataService rawDataService) {
        this.rawDataService = rawDataService;
    }


    @PostMapping("/data")
    public ResponseEntity<Void> receiveData(@RequestBody RawDataDTO data) {
        logger.debug("received data : {}", data);
        Long id = rawDataService.create(data);
        URI location = URI.create(String.format("/api/v1/data/%d", id));
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/datas")
    public ResponseEntity<Void> receiveDataBatch(@RequestBody List<RawDataDTO> datas) {
        datas.forEach((data) -> logger.debug("{}", data));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        datas.forEach(rawDataService::create);
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        rawDataService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
