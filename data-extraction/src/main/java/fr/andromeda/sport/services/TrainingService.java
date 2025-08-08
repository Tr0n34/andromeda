package fr.andromeda.sport.services;

import fr.andromeda.sport.dto.AggregateTrainingDTO;
import fr.andromeda.sport.dto.TrainingDTO;

public interface TrainingService {

    Long start(String deviceId);

    void stop(Long id, String deviceId);

    void pause();

    TrainingDTO findTraining(Long id);

    AggregateTrainingDTO fetchTrainingWitRowData(Long id);

}
