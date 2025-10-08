package fr.andromeda.api.enums;

public enum Header {

    REQUEST_ID("requestId", "request-ID", ""),
    CLIENT_VERSION("clientVersion", "client-Version", ""),
    DEVICE_ID("deviceId", "device-ID", ""),
    TENANT_ID("tenantId", "tenant-ID", ""),
    SIGNATURE("signature", "signature", ""),
    TIMESTAMP("timestamp", "timestamp", ""),
    LATENCY("latency", "latency", "");

    private String key;
    private String name;
    private String description;

    Header(String key, String name, String description) {
        this.key = key;
        this.name = name;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
