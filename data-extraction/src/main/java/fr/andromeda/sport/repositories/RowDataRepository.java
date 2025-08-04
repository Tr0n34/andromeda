package fr.andromeda.sport.repositories;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import fr.andromeda.sport.objects.entities.RowData;
import fr.andromeda.sport.objects.entities.RowData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RowDataRepository {

    private static final Logger logger = LoggerFactory.getLogger(RowDataRepository.class);

    private final InfluxDBClient influxDBClient;

    @Autowired
    public RowDataRepository(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
    }

    public void save(RowData data) {
        Point point = Point.measurement("row_data")
                .time(System.currentTimeMillis(), WritePrecision.MS)
                .addTag("id", String.valueOf(data.getId()))
                .addField("cadenceSpm", data.getCadenceSpm())
                .addField("strokeCount", data.getStrokeCount())
                .addField("powerW", data.getPowerW())
                .addField("caloriesKcal", data.getCaloriesKcal())
                .addField("heartRateBpm", data.getHeartRateBpm())
                .addField("elapsedTimeS", data.getElapsedTimeS());
        logger.debug("point={}", point.toLineProtocol());
        influxDBClient.makeWriteApi().writePoint(point);
    }

}
