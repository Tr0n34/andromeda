package fr.andromeda.sport.controllers;

import fr.andromeda.sport.objects.dto.TrainingDTO;
import fr.andromeda.sport.services.RowDataService;
import fr.andromeda.sport.services.TrainingService;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "${api.prefix}")
public class TrainingController {

    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

    private TrainingService trainingService;
    private RowDataService rowDataService;

    @Autowired
    public TrainingController(TrainingService trainingService, RowDataService rowDataService) {
        this.trainingService = trainingService;
        this.rowDataService = rowDataService;
    }

    @PostMapping(path = "/training")
    ResponseEntity<Long> start(TrainingDTO trainingDTO) {
        Long trainingId = trainingService.start(LocalDateTime.now());
        URI location = URI.create(String.format("/api/v1/training/%d", trainingId));
        return ResponseEntity.created(location).body(trainingId);
    }

    @GetMapping(path = "/training/{id}")
    ResponseEntity<TrainingDTO> get(@PathParam("id") Long id) {
        TrainingDTO trainingDTO = trainingService.fetchTraining(id);
        return ResponseEntity.ok(trainingDTO);
    }

}
