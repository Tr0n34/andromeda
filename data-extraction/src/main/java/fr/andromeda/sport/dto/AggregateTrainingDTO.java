package fr.andromeda.sport.dto;

import java.util.List;

public class AggregateTrainingDTO {

    private TrainingDTO trainingDTO;
    private List<RowDataDTO> rowDataDTOs;

    public AggregateTrainingDTO(TrainingDTO trainingDTO, List<RowDataDTO> rowDataDTO) {
        this.trainingDTO = trainingDTO;
        this.rowDataDTOs = rowDataDTO;
    }

    public TrainingDTO getTrainingDTO() {
        return trainingDTO;
    }

    public void setTrainingDTO(TrainingDTO trainingDTO) {
        this.trainingDTO = trainingDTO;
    }

    public List<RowDataDTO> getRowDataDTOs() {
        return rowDataDTOs;
    }

    public void setRowDataDTOs(List<RowDataDTO> rowDataDTOs) {
        this.rowDataDTOs = rowDataDTOs;
    }

    @Override
    public String toString() {
        return "AggregateTrainingDTO{" +
                "trainingDTO=" + trainingDTO +
                ", rowDataDTOs=" + rowDataDTOs +
                '}';
    }

}
