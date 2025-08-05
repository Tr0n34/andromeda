package fr.andromeda.sport.entities;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

@Measurement(name="rower")
public class RawDataEntity {

    @Column(tag = true)
    private Long id;
    @Column(tag = true)
    private String deviceId;
    @Column
    private int cadenceSpm;
    @Column
    private int strokeCount;
    @Column
    private int avgCadenceSpm;
    @Column
    private int distanceM;
    @Column
    private int powerW;
    @Column
    private int avgPowerW;
    @Column
    private int resistanceLevel;
    @Column
    private int caloriesKcal;
    @Column
    private int heartRateBpm;
    @Column
    private float elapsedTimeS;
    @Column
    private int remainingTimeS;
    @Column
    private int avgPace500mS;
    @Column
    private int instPace500mS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
