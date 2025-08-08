package fr.andromeda.sport.dto.factories;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.andromeda.sport.dto.RowDataDTO;
import fr.andromeda.sport.objects.RowData;

public class RowDataDTOBuilder {

    private Long trainingId;
    private String deviceId;
    private int cadenceSpm;
    private int strokeCount;
    private int avgCadenceSpm;
    private int distanceM;
    private int powerW;
    private int avgPowerW;
    private int resistanceLevel;
    private int caloriesKcal;
    private int heartRateBpm;
    private float elapsedTimeS;
    private int remainingTimeS;
    private int avgPace500mS;
    private int instPace500mS;

    private RowDataDTOBuilder() {

    }

    private RowDataDTOBuilder(Long trainingId,
                             String deviceId,
                             int cadenceSpm,
                             int strokeCount,
                             int avgCadenceSpm,
                             int distanceM,
                             int powerW,
                             int avgPowerW,
                             int resistanceLevel,
                             int caloriesKcal,
                             int heartRateBpm,
                             float elapsedTimeS,
                             int remainingTimeS,
                             int avgPace500mS,
                             int instPace500mS) {
        this.trainingId = trainingId;
        this.deviceId = deviceId;
        this.cadenceSpm = cadenceSpm;
        this.strokeCount = strokeCount;
        this.avgCadenceSpm = avgCadenceSpm;
        this.distanceM = distanceM;
        this.powerW = powerW;
        this.avgPowerW = avgPowerW;
        this.resistanceLevel = resistanceLevel;
        this.caloriesKcal = caloriesKcal;
        this.heartRateBpm = heartRateBpm;
        this.elapsedTimeS = elapsedTimeS;
        this.remainingTimeS = remainingTimeS;
        this.avgPace500mS = avgPace500mS;
        this.instPace500mS = instPace500mS;
    }

    public static RowDataDTOBuilder create() {
        return new RowDataDTOBuilder();
    }

    public RowDataDTO build() {
        return new RowDataDTO(trainingId,
                deviceId,
                cadenceSpm,
                strokeCount,
                avgCadenceSpm,
                distanceM,
                powerW,
                avgPowerW,
                resistanceLevel,
                caloriesKcal,
                heartRateBpm,
                elapsedTimeS,
                remainingTimeS,
                avgPace500mS,
                instPace500mS);
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public RowDataDTOBuilder setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public RowDataDTOBuilder setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public int getCadenceSpm() {
        return cadenceSpm;
    }

    public RowDataDTOBuilder setCadenceSpm(int cadenceSpm) {
        this.cadenceSpm = cadenceSpm;
        return this;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public RowDataDTOBuilder setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
        return this;
    }

    public int getAvgCadenceSpm() {
        return avgCadenceSpm;
    }

    public RowDataDTOBuilder setAvgCadenceSpm(int avgCadenceSpm) {
        this.avgCadenceSpm = avgCadenceSpm;
        return this;
    }

    public int getDistanceM() {
        return distanceM;
    }

    public RowDataDTOBuilder setDistanceM(int distanceM) {
        this.distanceM = distanceM;
        return this;
    }

    public int getPowerW() {
        return powerW;
    }

    public RowDataDTOBuilder setPowerW(int powerW) {
        this.powerW = powerW;
        return this;
    }

    public int getAvgPowerW() {
        return avgPowerW;
    }

    public RowDataDTOBuilder setAvgPowerW(int avgPowerW) {
        this.avgPowerW = avgPowerW;
        return this;
    }

    public int getResistanceLevel() {
        return resistanceLevel;
    }

    public RowDataDTOBuilder setResistanceLevel(int resistanceLevel) {
        this.resistanceLevel = resistanceLevel;
        return this;
    }

    public int getCaloriesKcal() {
        return caloriesKcal;
    }

    public RowDataDTOBuilder setCaloriesKcal(int caloriesKcal) {
        this.caloriesKcal = caloriesKcal;
        return this;
    }

    public int getHeartRateBpm() {
        return heartRateBpm;
    }

    public RowDataDTOBuilder setHeartRateBpm(int heartRateBpm) {
        this.heartRateBpm = heartRateBpm;
        return this;
    }

    public float getElapsedTimeS() {
        return elapsedTimeS;
    }

    public RowDataDTOBuilder setElapsedTimeS(float elapsedTimeS) {
        this.elapsedTimeS = elapsedTimeS;
        return this;
    }

    public int getRemainingTimeS() {
        return remainingTimeS;
    }

    public RowDataDTOBuilder setRemainingTimeS(int remainingTimeS) {
        this.remainingTimeS = remainingTimeS;
        return this;
    }

    public int getAvgPace500mS() {
        return avgPace500mS;
    }

    public RowDataDTOBuilder setAvgPace500mS(int avgPace500mS) {
        this.avgPace500mS = avgPace500mS;
        return this;
    }

    public int getInstPace500mS() {
        return instPace500mS;
    }

    public RowDataDTOBuilder setInstPace500mS(int instPace500mS) {
        this.instPace500mS = instPace500mS;
        return this;
    }
}
