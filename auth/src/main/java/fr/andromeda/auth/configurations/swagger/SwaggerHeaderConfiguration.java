package fr.andromeda.auth.configurations.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "swagger.header.default")
public class SwaggerHeaderConfiguration {

    private String requestId;
    private String clientVersion;
    private String deviceId;
    private String tenantId;
    private String signature;
    private String timestamp;

    public SwaggerHeaderConfiguration() {

    }

    public SwaggerHeaderConfiguration(String requestId, String clientVersion, String deviceId,
                                      String tenantId, String signature, String timestamp) {
        this.requestId = requestId;
        this.clientVersion = clientVersion;
        this.deviceId = deviceId;
        this.tenantId = tenantId;
        this.signature = signature;
        this.timestamp = timestamp;
    }

    public String getRequestId() {
        return requestId;
    }

    public SwaggerHeaderConfiguration setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public SwaggerHeaderConfiguration setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public SwaggerHeaderConfiguration setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SwaggerHeaderConfiguration setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public SwaggerHeaderConfiguration setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public SwaggerHeaderConfiguration setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}