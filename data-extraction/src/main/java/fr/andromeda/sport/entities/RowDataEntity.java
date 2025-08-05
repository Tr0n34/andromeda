package fr.andromeda.sport.entities;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

@Measurement(name="row_data")
public class RowDataEntity {

    @Column(tag = true)
    private Long id;
    @Column
    private Long deviceId;
    @Column
    private int cadenceSpm;
    @Column
    private int strokeCount;
    @Column
    private int powerW;
    @Column
    private int caloriesKcal;
    @Column
    private int heartRateBpm;
    @Column
    private float elapsedTimeS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public int getCaloriesKcal() {
        return caloriesKcal;
    }

    public void setCaloriesKcal(int caloriesKcal) {
        this.caloriesKcal = caloriesKcal;
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

    public int getPowerW() {
        return powerW;
    }

    public void setPowerW(int powerW) {
        this.powerW = powerW;
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


}
