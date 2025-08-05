package fr.andromeda.sport.services;

import fr.andromeda.sport.objects.dto.TrainingDTO;

import java.time.LocalDateTime;

public interface TrainingService {

    Long start(LocalDateTime startedOn);

    void stop(TrainingDTO trainingDTO, LocalDateTime finishedOn);

    void pause();

    void addRowData();

    TrainingDTO fetchTraining(Long id);

}
