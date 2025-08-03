package fr.andromeda.sport.controllers;


import fr.andromeda.sport.objects.dto.RowDataDTO;
import fr.andromeda.sport.services.RowDataService;
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

    private final RowDataService rowDataService;

    @Autowired
    public ConsumeDataController(RowDataService rowDataService) {
        this.rowDataService = rowDataService;
    }


    @PostMapping("/data")
    public ResponseEntity<Void> receiveData(@RequestBody RowDataDTO data) {
        logger.debug("recieve data : {}", data.toString());
        Long id = rowDataService.create(data);
        URI location = URI.create("/api/v1/data/" + id);
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/datas")
    public ResponseEntity<Void> receiveDataBatch(@RequestBody List<RowDataDTO> datas) {
        datas.forEach((data) -> logger.debug("{}", data.toString()));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        datas.forEach(rowDataService::create);
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        rowDataService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
