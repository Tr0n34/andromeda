package fr.andromeda.sport.dto;

import fr.andromeda.sport.objects.RowData;

public class RowDataDTO extends RowData implements IDTO {

    public RowDataDTO() {
        super();
    }

    public RowDataDTO(Long trainingId,
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
        super.setTrainingId(trainingId);
        super.setDeviceId(deviceId);
        super.setCadenceSpm(cadenceSpm);
        super.setStrokeCount(strokeCount);
        super.setAvgCadenceSpm(avgCadenceSpm);
        super.setDistanceM(distanceM);
        super.setPowerW(powerW);
        super.setAvgPowerW(avgPowerW);
        super.setResistanceLevel(resistanceLevel);
        super.setCaloriesKcal(caloriesKcal);
        super.setHeartRateBpm(heartRateBpm);
        super.setElapsedTimeS(elapsedTimeS);
        super.setRemainingTimeS(remainingTimeS);
        super.setAvgPace500mS(avgPace500mS);
        super.setInstPace500mS(instPace500mS);
    }
    
}
