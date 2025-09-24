package fr.andromeda.sport.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.andromeda.sport.enums.TrainingStatus;

import java.time.LocalDateTime;

public class TrainingDTO implements IDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("startedOn")
    private LocalDateTime startedOn;
    @JsonProperty("finishedOn")
    private LocalDateTime finishedOn;
    @JsonProperty("status")
    private TrainingStatus status;

    public TrainingDTO() {
    }

    public TrainingDTO(Long id, String deviceId, LocalDateTime startedOn, LocalDateTime finishedOn, TrainingStatus status) {
        this.id = id;
        this.deviceId = deviceId;
        this.startedOn = startedOn;
        this.finishedOn = finishedOn;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public TrainingDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public TrainingDTO setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public TrainingDTO setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }

    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public TrainingDTO setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
        return this;
    }

    public TrainingStatus getStatus() {
        return status;
    }

    public TrainingDTO setStatus(TrainingStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "TrainingDTO{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", startedOn=" + startedOn +
                ", finishedOn=" + finishedOn +
                ", status=" + status +
                '}';
    }

}
