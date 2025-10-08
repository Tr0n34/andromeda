package fr.andromeda.auth.configurations.swagger;

import fr.andromeda.api.enums.Header;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfiguration {

    private static final String HEADER = "header";

    @Value("${app.swagger.servers}")
    private String[] servers;

    private final SwaggerHeaderConfiguration defaultHeader;

    public SwaggerConfiguration(SwaggerHeaderConfiguration defaultHeader) {
        this.defaultHeader = defaultHeader;
    }

    @Bean
    public OpenApiCustomizer customSwagger() {
        return openApi -> {
            applyServers(openApi);
            addHeadersToComponents(openApi);
            applyGlobalHeaderRefs(openApi);
        };
    }

    private void applyServers(OpenAPI openApi) {
        if (servers != null && servers.length > 0) {
            openApi.setServers(Arrays.stream(servers)
                    .map(url -> new Server().url(url))
                    .toList());
        }
    }

    private void addHeadersToComponents(OpenAPI openApi) {
        if (openApi.getComponents() == null) {
            openApi.setComponents(new io.swagger.v3.oas.models.Components());
        }
        openApi.getComponents()
                .addParameters("requestIdHeader", createParameter(Header.REQUEST_ID.getName(), defaultHeader.getRequestId(), Header.REQUEST_ID.getDescription()))
                .addParameters("clientVersionHeader", createParameter(Header.CLIENT_VERSION.getName(), defaultHeader.getClientVersion(), Header.CLIENT_VERSION.getDescription()))
                .addParameters("deviceIdHeader", createParameter(Header.DEVICE_ID.getName(), defaultHeader.getDeviceId(), Header.DEVICE_ID.getDescription()))
                .addParameters("tenantIdHeader", createParameter(Header.TENANT_ID.getName(), defaultHeader.getTenantId(), Header.TENANT_ID.getDescription()))
                .addParameters("signatureHeader", createParameter(Header.SIGNATURE.getName(), defaultHeader.getSignature(), Header.SIGNATURE.getDescription()))
                .addParameters("timestampHeader", createParameter(Header.TIMESTAMP.getName(), LocalDateTime.now().toString(), Header.TIMESTAMP.getDescription()));
    }

    private Parameter createParameter(String name, String example, String description) {
        return new Parameter()
                .in(HEADER)
                .name(name)
                .description(description)
                .required(true)
                .schema(new StringSchema().example(example));
    }

    private void applyGlobalHeaderRefs(OpenAPI openApi) {
        if (openApi.getPaths() == null) return;

        List<String> headerRefs = Arrays.asList(
                "requestIdHeader",
                "clientVersionHeader",
                "deviceIdHeader",
                "tenantIdHeader",
                "signatureHeader",
                "timestampHeader"
        );

        openApi.getPaths().values().forEach(pathItem ->
                pathItem.readOperations().forEach(operation ->
                        headerRefs.forEach(ref -> operation.addParametersItem(new Parameter().$ref("#/components/parameters/" + ref)))
                )
        );
    }

}