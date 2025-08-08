package fr.andromeda.sport.configurations.influxdb;

import java.util.ArrayList;
import java.util.List;

public class FluxQueryBuilder {

    public static final String RANGE_QUERY_DEFAULT = "-1h";

    private final String bucket;
    private final String measurement;
    private final List<String> filters;
    private String range;
    private final List<String> pipes;

    public FluxQueryBuilder(String bucket, String measurement) {
        this.bucket = bucket;
        this.measurement = measurement;
        filters = new ArrayList<>();
        pipes = new ArrayList<>();
        range = RANGE_QUERY_DEFAULT;
    }

    public FluxQueryBuilder withRange(String range) {
        this.range = range;
        return this;
    }

    public FluxQueryBuilder filterTag(String key, String value) {
        filters.add(String.format("r.%s == \"%s\"", key, value));
        return this;
    }

    public FluxQueryBuilder filterField(String field) {
        filters.add(String.format("r._field == \"%s\"", field));
        return this;
    }

    public FluxQueryBuilder additionalPipe(String pipeLine) {
        pipes.add(pipeLine.trim());
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("from(bucket: \"%s\")\n", bucket));
        sb.append(String.format("  |> range(start: %s)\n", range));
        sb.append(String.format("  |> filter(fn: (r) => r._measurement == \"%s\"", measurement));

        if (!filters.isEmpty()) {
            sb.append(" and ").append(String.join(" and ", filters));
        }

        sb.append(")\n");

        for (String pipe : pipes) {
            sb.append("  |> ").append(pipe).append("\n");
        }

        return sb.toString();
    }
}
