package fr.andromeda.sport.dto;

import java.util.List;

public class AggregateTrainingDTO implements IDTO {

    private TrainingDTO training;
    private List<RowDataDTO> rowData;

    public AggregateTrainingDTO(TrainingDTO training, List<RowDataDTO> rowData) {
        this.training = training;
        this.rowData = rowData;
    }

    public TrainingDTO getTraining() {
        return training;
    }

    public AggregateTrainingDTO setTraining(TrainingDTO training) {
        this.training = training;
        return this;
    }

    public List<RowDataDTO> getRowData() {
        return rowData;
    }

    public AggregateTrainingDTO setRowData(List<RowDataDTO> rowData) {
        this.rowData = rowData;
        return this;
    }

    @Override
    public String toString() {
        return "AggregateTrainingDTO{" +
                "training=" + training +
                ", rowData=" + rowData +
                '}';
    }

}
