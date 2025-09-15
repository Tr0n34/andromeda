package fr.andromeda.sport.services;

import fr.andromeda.sport.dto.AggregateTrainingDTO;
import fr.andromeda.sport.dto.TrainingDTO;

import java.util.List;

public interface TrainingService {

    Long start(String deviceId);

    void stop(Long id);

    void pause(Long id);

    void resume(Long id);

    TrainingDTO findTraining(Long id);

    AggregateTrainingDTO fetchTrainingWitRowData(Long id);

    List<TrainingDTO> findAll();

}
