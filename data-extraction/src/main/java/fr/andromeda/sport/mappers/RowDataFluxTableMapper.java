package fr.andromeda.sport.mappers;

import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import fr.andromeda.sport.entities.RowDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RowDataFluxTableMapper {

    private static final Logger logger = LoggerFactory.getLogger(RowDataFluxTableMapper.class);

    public static List<RowDataEntity> of(List<FluxTable> tables) {
        List<RowDataEntity> result = new ArrayList<>();
        for (FluxTable table : tables) {
            for (FluxRecord record : table.getRecords()) {
                RowDataEntity entity = new RowDataEntity();
                entity.setTrainingId(parseNumberSafe(record.getValueByKey("trainingId"), Long::valueOf));
                entity.setDeviceId(parseStringSafe(record.getValueByKey("deviceId")));
                entity.setCadenceSpm(parseNumberSafe(record.getValueByKey("cadenceSpm"), Integer::valueOf));
                entity.setStrokeCount(parseNumberSafe(record.getValueByKey("strokeCount"), Integer::valueOf));
                entity.setAvgCadenceSpm(parseNumberSafe(record.getValueByKey("avgCadenceSpm"), Integer::valueOf));
                entity.setAvgPace500mS(parseNumberSafe(record.getValueByKey("avgPace500mS"), Integer::valueOf));
                entity.setAvgPowerW(parseNumberSafe(record.getValueByKey("avgPowerW"), Integer::valueOf));
                entity.setCaloriesKcal(parseNumberSafe(record.getValueByKey("caloriesKcal"), Integer::valueOf));
                entity.setDistanceM(parseNumberSafe(record.getValueByKey("distanceM"), Integer::valueOf));
                entity.setElapsedTimeS(parseNumberSafe(record.getValueByKey("elapsedTimeS"), Float::valueOf));
                entity.setHeartRateBpm(parseNumberSafe(record.getValueByKey("heartRateBpm"), Integer::valueOf));
                entity.setPowerW(parseNumberSafe(record.getValueByKey("powerW"), Integer::valueOf));
                entity.setResistanceLevel(parseNumberSafe(record.getValueByKey("resistanceLevel"), Integer::valueOf));
                entity.setInstPace500mS(parseNumberSafe(record.getValueByKey("instPace500mS"), Integer::valueOf));
                entity.setRemainingTimeS(parseNumberSafe(record.getValueByKey("remainingTimeS"), Integer::valueOf));
                result.add(entity);
            }
        }
        return result;
    }

    public static <T extends Number> T parseNumberSafe(Object value, Function<String, T> parser) {
        if ( value == null ) {
            return null;
        }
        try {
            return parser.apply(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String parseStringSafe(Object val) {
        if (val == null) return null;
        try {
            return val.toString();
        } catch (Exception e) {
            return null;
        }
    }


}
