package fr.andromeda.sport.repositories;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteOptions;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import fr.andromeda.sport.entities.RawDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RawDataRepository {

    private static final Logger logger = LoggerFactory.getLogger(RawDataRepository.class);

    public static final String MEASUREMENT_NAME = "rower";

    private final InfluxDBClient influxDBClient;
    private final WriteApi writeApi;

    @Autowired
    public RawDataRepository(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
        this.writeApi = influxDBClient.makeWriteApi(WriteOptions.DEFAULTS);
    }

    public void save(RawDataEntity data) {
        Point point = Point.measurement(MEASUREMENT_NAME)
                .time(System.currentTimeMillis(), WritePrecision.MS)
                .addTag("id", String.valueOf(data.getId()))
                .addTag("deviceId", data.getDeviceId())
                .addField("cadenceSpm", data.getCadenceSpm())
                .addField("strokeCount", data.getStrokeCount())
                .addField("avgCadenceSpm", data.getAvgCadenceSpm())
                .addField("distanceM", data.getDistanceM())
                .addField("powerW", data.getPowerW())
                .addField("avgPowerW", data.getAvgPowerW())
                .addField("resistanceLevel", data.getResistanceLevel())
                .addField("caloriesKcal", data.getCaloriesKcal())
                .addField("heartRateBpm", data.getHeartRateBpm())
                .addField("elapsedTimeS", data.getElapsedTimeS())
                .addField("remainingTimeS", data.getRemainingTimeS())
                .addField("avgPace500mS", data.getAvgPace500mS())
                .addField("instPace500mS", data.getInstPace500mS());
        logger.debug("Point={}", point.toLineProtocol());
        writeApi.writePoint(point);
    }

}
