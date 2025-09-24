package fr.andromeda.sport.dto.builders;

import fr.andromeda.sport.dto.TrainingDTO;
import fr.andromeda.sport.enums.TrainingStatus;

import java.time.LocalDateTime;

public class TrainingDTOBuilder {

    private Long id;
    private String deviceId;
    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;
    private TrainingStatus status;

    private TrainingDTOBuilder() {

    }

    public static TrainingDTO of(String deviceId) {
        return new TrainingDTO().setDeviceId(deviceId);
    }

    public static TrainingDTO of(Long id, String deviceId) {
        return new TrainingDTO().setId(id).setDeviceId(deviceId);
    }

    public static TrainingDTOBuilder create() {
        return new TrainingDTOBuilder();
    }

    public TrainingDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public TrainingDTOBuilder setStatus(TrainingStatus status) {
        this.status = status;
        return this;
    }

    public TrainingDTOBuilder setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public TrainingDTOBuilder setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }

    public TrainingDTOBuilder setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
        return this;
    }

    public TrainingDTO build() {
        return new TrainingDTO(id, deviceId, startedOn, finishedOn, status);
    }

}
