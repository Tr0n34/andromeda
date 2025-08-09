package fr.andromeda.sport.repositories;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteOptions;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;
import fr.andromeda.sport.configurations.influxdb.FluxQueryBuilder;
import fr.andromeda.sport.entities.RowDataEntity;
import fr.andromeda.sport.mappers.RowDataFluxTableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RowDataRepository {

    private static final Logger logger = LoggerFactory.getLogger(RowDataRepository.class);

    public static final String MEASUREMENT_NAME = "rower";
    private static final String BUCKET_NAME = "raw-data";

    private final InfluxDBClient influxDBClient;
    private final WriteApi writeApi;
    private final QueryApi queryApi;

    @Autowired
    public RowDataRepository(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
        this.writeApi = influxDBClient.makeWriteApi(WriteOptions.DEFAULTS);
        this.queryApi = influxDBClient.getQueryApi();
    }

    public void save(RowDataEntity data) {
        Point point = Point.measurement(MEASUREMENT_NAME)
                .time(System.currentTimeMillis(), WritePrecision.MS)
                .addTag("trainingId", String.valueOf(data.getTrainingId()))
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

    public Optional<List<RowDataEntity>> findByTrainingId(String trainingId) {
        String query = new FluxQueryBuilder(BUCKET_NAME, MEASUREMENT_NAME)
                .withRange("-24h")
                .filterTag("trainingId", trainingId)
                .additionalPipe("aggregateWindow(every: 1m, fn: mean, createEmpty: false)")
                .build();
        List<FluxTable> fluxTables = queryApi.query(query);
        return Optional.of(RowDataFluxTableMapper.of(fluxTables));
    }

    public Optional<List<RowDataEntity>> findByDeviceId(String deviceId) {
        String query = new FluxQueryBuilder(BUCKET_NAME, MEASUREMENT_NAME)
                .withRange("-24h")
                .filterTag("deviceId", deviceId)
                .additionalPipe("aggregateWindow(every: 1m, fn: mean, createEmpty: false)")
                .additionalPipe("group(columns: [\"trainingId\"])")
                .build();
        List<FluxTable> fluxTables = queryApi.query(query);
        return Optional.of(RowDataFluxTableMapper.of(fluxTables));
    }

}
