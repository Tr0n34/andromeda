package fr.andromeda.cyb.batch.parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.andromeda.cyb.dto.ApplicationParameterDTO;
import fr.andromeda.cyb.repositories.ApplicationConfigurationRepository;
import fr.andromeda.cyb.services.interfaces.IApplicationParameterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.List;

public class ApplicationParameterPopulator implements ApplicationRunner {

    @Value("classpath:${resources.application.parameters.file:/application-parameter.json}")
    private Resource roleFile;

    private final ApplicationConfigurationRepository applicationConfigurationRepository;
    private final IApplicationParameterService applicationParameterService;
    private final ObjectMapper objectMapper;

    public ApplicationParameterPopulator(ApplicationConfigurationRepository applicationConfigurationRepository, IApplicationParameterService applicationParameterService, ObjectMapper objectMapper) {
        this.applicationConfigurationRepository = applicationConfigurationRepository;
        this.applicationParameterService = applicationParameterService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        applicationConfigurationRepository.deleteAll();
        try (InputStream is = roleFile.getInputStream()) {
            List<ApplicationParameterDTO> applicationParameters = objectMapper.readValue(is, new TypeReference<>() {});
            if (!applicationParameters.isEmpty()) {
                applicationParameterService.createAll(applicationParameters);
            }
        }
    }

}
