package fr.andromeda.sport.configurations.health;

import com.influxdb.client.InfluxDBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class InfluxDBHealthIndicator implements HealthIndicator, SmartLifecycle {

    private final InfluxDBClient influxDBClient;
    private boolean running = false;

    @Autowired
    public InfluxDBHealthIndicator(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
    }

    @Override
    public Health health() {
        try {
            if (influxDBClient.ping()) {
                return Health.up()
                        .withDetail("version", influxDBClient.version())
                        .build();
            }
            return Health.down().withDetail("error", "Ping failed").build();
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }

    @Override
    public void start() {
        if ( !influxDBClient.ping() ) {
            throw new IllegalStateException("After context started, influxDBClient is not ready");
        }
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return SmartLifecycle.super.getPhase();
    }

}