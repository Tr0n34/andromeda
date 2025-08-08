package fr.andromeda.sport.controllers;

import fr.andromeda.sport.dto.AggregateTrainingDTO;
import fr.andromeda.sport.dto.TrainingDTO;
import fr.andromeda.sport.services.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "${api.prefix}/trainings")
public class TrainingController {

    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

    private TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping
    ResponseEntity<Long> start(@RequestBody TrainingDTO trainingDTO) {
        Long trainingId = trainingService.start(trainingDTO.getDeviceId());
        URI location = ServletUriComponentsBuilder.fromPath("/api/v1/trainings")
                .path("/{id}")
                .buildAndExpand(trainingId)
                .toUri();
        return ResponseEntity.created(location).body(trainingId);
    }

    @PatchMapping(path = "/{id}/stop")
    ResponseEntity<Void> stop(Long trainingId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{id}/pause")
    ResponseEntity<Void> pause(Long trainingId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{id}/resume")
    ResponseEntity<Void> resume(Long trainingId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}/aggregate")
    ResponseEntity<AggregateTrainingDTO> fetchTrainingAndRowData(@PathVariable("id") Long id) {
        AggregateTrainingDTO aggregateTrainingDTO = trainingService.fetchTrainingWitRowData(id);
        return ResponseEntity.ok(aggregateTrainingDTO);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<TrainingDTO> get(@PathVariable("id") Long id) {
        TrainingDTO trainingDTO = trainingService.findTraining(id);
        logger.debug("{}", trainingDTO);
        return ResponseEntity.ok(trainingDTO);
    }

}
