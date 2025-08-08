package fr.andromeda.sport.entities;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

@Measurement(name="rower")
public class RowDataEntity implements IEntity {

    @Column(tag = true)
    private Long trainingId;
    @Column(tag = true)
    private String deviceId;
    @Column
    private Integer cadenceSpm;
    @Column
    private Integer strokeCount;
    @Column
    private Integer avgCadenceSpm;
    @Column
    private Integer distanceM;
    @Column
    private Integer powerW;
    @Column
    private Integer avgPowerW;
    @Column
    private Integer resistanceLevel;
    @Column
    private Integer caloriesKcal;
    @Column
    private Integer heartRateBpm;
    @Column
    private Float elapsedTimeS;
    @Column
    private Integer remainingTimeS;
    @Column
    private Integer avgPace500mS;
    @Column
    private Integer instPace500mS;

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

    public Integer getCadenceSpm() {
        return cadenceSpm;
    }

    public void setCadenceSpm(Integer cadenceSpm) {
        this.cadenceSpm = cadenceSpm;
    }

    public Integer getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(Integer strokeCount) {
        this.strokeCount = strokeCount;
    }

    public Integer getAvgCadenceSpm() {
        return avgCadenceSpm;
    }

    public void setAvgCadenceSpm(Integer avgCadenceSpm) {
        this.avgCadenceSpm = avgCadenceSpm;
    }

    public Integer getDistanceM() {
        return distanceM;
    }

    public void setDistanceM(Integer distanceM) {
        this.distanceM = distanceM;
    }

    public Integer getPowerW() {
        return powerW;
    }

    public void setPowerW(Integer powerW) {
        this.powerW = powerW;
    }

    public Integer getAvgPowerW() {
        return avgPowerW;
    }

    public void setAvgPowerW(Integer avgPowerW) {
        this.avgPowerW = avgPowerW;
    }

    public Integer getResistanceLevel() {
        return resistanceLevel;
    }

    public void setResistanceLevel(Integer resistanceLevel) {
        this.resistanceLevel = resistanceLevel;
    }

    public Integer getCaloriesKcal() {
        return caloriesKcal;
    }

    public void setCaloriesKcal(Integer caloriesKcal) {
        this.caloriesKcal = caloriesKcal;
    }

    public Integer getHeartRateBpm() {
        return heartRateBpm;
    }

    public void setHeartRateBpm(Integer heartRateBpm) {
        this.heartRateBpm = heartRateBpm;
    }

    public Float getElapsedTimeS() {
        return elapsedTimeS;
    }

    public void setElapsedTimeS(Float elapsedTimeS) {
        this.elapsedTimeS = elapsedTimeS;
    }

    public Integer getRemainingTimeS() {
        return remainingTimeS;
    }

    public void setRemainingTimeS(Integer remainingTimeS) {
        this.remainingTimeS = remainingTimeS;
    }

    public Integer getAvgPace500mS() {
        return avgPace500mS;
    }

    public void setAvgPace500mS(Integer avgPace500mS) {
        this.avgPace500mS = avgPace500mS;
    }

    public Integer getInstPace500mS() {
        return instPace500mS;
    }

    public void setInstPace500mS(Integer instPace500mS) {
        this.instPace500mS = instPace500mS;
    }
}
