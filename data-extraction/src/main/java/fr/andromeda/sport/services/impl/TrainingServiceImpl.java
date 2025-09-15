package fr.andromeda.sport.services.impl;

import fr.andromeda.sport.dto.AggregateTrainingDTO;
import fr.andromeda.sport.dto.RowDataDTO;
import fr.andromeda.sport.dto.builders.TrainingDTOBuilder;
import fr.andromeda.sport.entities.TrainingEntity;
import fr.andromeda.sport.enums.TrainingStatus;
import fr.andromeda.sport.exceptions.business.ResourceNotFoundException;
import fr.andromeda.sport.mappers.RowDataMapper;
import fr.andromeda.sport.mappers.TrainingMapper;
import fr.andromeda.sport.dto.TrainingDTO;
import fr.andromeda.sport.repositories.RowDataRepository;
import fr.andromeda.sport.repositories.TrainingRepository;
import fr.andromeda.sport.services.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final RowDataRepository rowDataRepository;
    private final RowDataMapper rowDataMapper;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository,
                               RowDataRepository rowDataRepository,
                               TrainingMapper trainingMapper,
                               RowDataMapper rowDataMapper) {
        this.trainingRepository = trainingRepository;
        this.rowDataRepository = rowDataRepository;
        this.trainingMapper = trainingMapper;
        this.rowDataMapper = rowDataMapper;
    }

    @Override
    public Long start(String deviceId) {
        TrainingEntity trainingEntity = trainingMapper.toEntity(TrainingDTOBuilder.of(deviceId));
        trainingEntity.setStartedOn(LocalDateTime.now());
        trainingEntity.setStatus(TrainingStatus.STARTED);
        logger.debug("{}", trainingEntity);
        return trainingRepository.save(trainingEntity).getId();
    }

    @Override
    public void stop(Long trainingId) {
        trainingRepository.findById(trainingId).ifPresent(
            (training) -> {
                training.setStatus(TrainingStatus.STOPPED);
                training.setFinishedOn(LocalDateTime.now());
                logger.debug("{}", training);
                trainingRepository.save(training);
            }
        );
    }

    @Override
    public void pause(Long trainingId) {
        changeStatus(trainingId, TrainingStatus.RESUMED);
    }

    @Override
    public void resume(Long trainingId) {
        changeStatus(trainingId, TrainingStatus.RESUMED);
    }

    private void changeStatus(Long trainingId, TrainingStatus status) {
        trainingRepository.findById(trainingId).ifPresent(
                (training) -> {
                    training.setStatus(status);
                    logger.debug("{}", training);
                    trainingRepository.save(training);
                }
        );
    }

    @Override
    public TrainingDTO findTraining(Long id) {
        TrainingEntity trainingEntity = trainingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id + " n'existe pas.")
        );
        logger.debug("{}", trainingEntity);
        return trainingMapper.toDto(trainingEntity);
    }

    @Override
    public AggregateTrainingDTO fetchTrainingWitRowData(Long id) {
        TrainingDTO trainingDTO = trainingMapper.toDto(trainingRepository.findById(id).orElseThrow());
        List<RowDataDTO> rowDataDTOs = rowDataMapper.toDtoList(rowDataRepository.findByTrainingId(Long.toString(id)).orElseThrow());
        AggregateTrainingDTO aggregateTrainingDTO = new AggregateTrainingDTO(trainingDTO, rowDataDTOs);
        logger.debug("{}", aggregateTrainingDTO);
        return aggregateTrainingDTO;
    }

    @Override
    public List<TrainingDTO> findAll() {
        return trainingMapper.toDtoList(trainingRepository.findAll());
    }


}
