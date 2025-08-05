package fr.andromeda.sport.services.impl;

import fr.andromeda.sport.entities.TrainingEntity;
import fr.andromeda.sport.objects.Training;
import fr.andromeda.sport.objects.dto.TrainingDTO;
import fr.andromeda.sport.repositories.TrainingRepository;
import fr.andromeda.sport.services.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Long start(LocalDateTime startedOn) {
        TrainingEntity trainingEntity = new TrainingEntity();
        trainingEntity.setStartedOn(startedOn);
        logger.debug("{}", trainingEntity);
        return trainingRepository.save(trainingEntity).getId();
    }

    @Override
    public void stop(TrainingDTO trainingDTO, LocalDateTime finishedOn) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void addRowData() {

    }

    @Override
    public TrainingDTO fetchTraining(Long id) {
        TrainingEntity trainingEntity = trainingRepository.findById(id).orElseThrow();
        return null;
    }
}
