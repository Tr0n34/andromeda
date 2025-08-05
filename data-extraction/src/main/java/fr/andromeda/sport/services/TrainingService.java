package fr.andromeda.sport.services;

import fr.andromeda.sport.objects.Training;
import fr.andromeda.sport.objects.dto.RowDataDTO;
import fr.andromeda.sport.objects.dto.TrainingDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainingService {

    Long start(LocalDateTime startedOn);

    void stop(TrainingDTO trainingDTO, LocalDateTime finishedOn);

    void pause();

    void addRowData();

    TrainingDTO fetchTraining(Long id);

}
