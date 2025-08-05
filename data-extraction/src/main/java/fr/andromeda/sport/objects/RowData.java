package fr.andromeda.sport.objects;

public class RowData {

    private Long id;
    private String deviceId;
    private int cadenceSpm;
    private int strokeCount;
    private int powerW;
    private int caloriesKcal;
    private int heartRateBpm;
    private float elapsedTimeS;

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

    @Override
    public String toString() {
        return "RowDataDTO{" +
                ", cadenceSpm=" + cadenceSpm +
                ", strokeCount=" + strokeCount +
                ", powerW=" + powerW +
                ", caloriesKcal=" + caloriesKcal +
                ", heartRateBpm=" + heartRateBpm +
                ", elapsedTimeS=" + elapsedTimeS +
                '}';
    }
    
}