package fr.andromeda.sport.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RowData {

    @JsonProperty("training_id")
    private Long trainingId;

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("cadence_spm")
    private int cadenceSpm;

    @JsonProperty("stroke_count")
    private int strokeCount;

    @JsonProperty("avg_cadence_spm")
    private int avgCadenceSpm;

    @JsonProperty("distance_m")
    private int distanceM;

    @JsonProperty("power_w")
    private int powerW;

    @JsonProperty("avg_power_w")
    private int avgPowerW;

    @JsonProperty("resistance_level")
    private int resistanceLevel;

    @JsonProperty("calories_kcal")
    private int caloriesKcal;

    @JsonProperty("heart_rate_bpm")
    private int heartRateBpm;

    @JsonProperty("elapsed_time_s")
    private float elapsedTimeS;

    @JsonProperty("remaining_time_s")
    private int remainingTimeS;

    @JsonProperty("avg_pace_500m_s")
    private int avgPace500mS;

    @JsonProperty("inst_pace_500m_s")
    private int instPace500mS;

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getCadenceSpm() {
        return cadenceSpm;
    }

    public void setCadenceSpm(int cadenceSpm) {
        this.cadenceSpm = cadenceSpm;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
    }

    public int getAvgCadenceSpm() {
        return avgCadenceSpm;
    }

    public void setAvgCadenceSpm(int avgCadenceSpm) {
        this.avgCadenceSpm = avgCadenceSpm;
    }

    public int getDistanceM() {
        return distanceM;
    }

    public void setDistanceM(int distanceM) {
        this.distanceM = distanceM;
    }

    public int getPowerW() {
        return powerW;
    }

    public void setPowerW(int powerW) {
        this.powerW = powerW;
    }

    public int getAvgPowerW() {
        return avgPowerW;
    }

    public void setAvgPowerW(int avgPowerW) {
        this.avgPowerW = avgPowerW;
    }

    public int getResistanceLevel() {
        return resistanceLevel;
    }

    public void setResistanceLevel(int resistanceLevel) {
        this.resistanceLevel = resistanceLevel;
    }

    public int getCaloriesKcal() {
        return caloriesKcal;
    }

    public void setCaloriesKcal(int caloriesKcal) {
        this.caloriesKcal = caloriesKcal;
    }

    public int getHeartRateBpm() {
        return heartRateBpm;
    }

    public void setHeartRateBpm(int heartRateBpm) {
        this.heartRateBpm = heartRateBpm;
    }

    public float getElapsedTimeS() {
        return elapsedTimeS;
    }

    public void setElapsedTimeS(float elapsedTimeS) {
        this.elapsedTimeS = elapsedTimeS;
    }

    public int getRemainingTimeS() {
        return remainingTimeS;
    }

    public void setRemainingTimeS(int remainingTimeS) {
        this.remainingTimeS = remainingTimeS;
    }

    public int getAvgPace500mS() {
        return avgPace500mS;
    }

    public void setAvgPace500mS(int avgPace500mS) {
        this.avgPace500mS = avgPace500mS;
    }

    public int getInstPace500mS() {
        return instPace500mS;
    }

    public void setInstPace500mS(int instPace500mS) {
        this.instPace500mS = instPace500mS;
    }

    @Override
    public String toString() {
        return "RawData{" +
                "trainingId=" + trainingId +
                ", deviceId='" + deviceId + '\'' +
                ", cadenceSpm=" + cadenceSpm +
                ", strokeCount=" + strokeCount +
                ", avgCadenceSpm=" + avgCadenceSpm +
                ", distanceM=" + distanceM +
                ", powerW=" + powerW +
                ", avgPowerW=" + avgPowerW +
                ", resistanceLevel=" + resistanceLevel +
                ", caloriesKcal=" + caloriesKcal +
                ", heartRateBpm=" + heartRateBpm +
                ", elapsedTimeS=" + elapsedTimeS +
                ", remainingTimeS=" + remainingTimeS +
                ", avgPace500mS=" + avgPace500mS +
                ", instPace500mS=" + instPace500mS +
                '}';
    }

}