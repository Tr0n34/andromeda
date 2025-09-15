package fr.andromeda.sport.controllers;

import fr.andromeda.sport.dto.AggregateTrainingDTO;
import fr.andromeda.sport.dto.TrainingDTO;
import fr.andromeda.sport.services.TrainingService;
import io.micrometer.core.instrument.util.StringEscapeUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}/trainings")
@Tag(name = "Training", description = "Training Session Manager")
public class TrainingController {

    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

    private TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping
    @Operation(summary = "Start a training", description = "start a training and return the ID")
    ResponseEntity<String> start(@RequestBody TrainingDTO trainingDTO) {
        Long trainingId = trainingService.start(trainingDTO.getDeviceId());
        URI location = ServletUriComponentsBuilder.fromPath("/api/v1/trainings")
                .path("/{id}")
                .buildAndExpand(trainingId)
                .toUri();
        logger.debug("{}", trainingDTO);
        return ResponseEntity.created(location).body(trainingId.toString());
    }

    @PatchMapping(path = "/{id}/stop")
    @Operation(summary = "Stop a training", description = "Stop a training without return")
    ResponseEntity<Void> stop(@PathVariable("id") Long trainingId) {
        trainingService.stop(trainingId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/pause")
    @Operation(summary = "Pause a training", description = "Pause a training")
    ResponseEntity<Void> pause(Long trainingId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{id}/resume")
    @Operation(summary = "Resume a paused training", description = "Resume a paused training without return")
    ResponseEntity<Void> resume(Long trainingId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}/aggregate")
    @Operation(summary = "Aggregate a training with its time data", description = "start a training and return the ID")
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

    @GetMapping
    ResponseEntity<List<TrainingDTO>> getAll() {
        return ResponseEntity.ok(trainingService.findAll());
    }

}
