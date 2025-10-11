package fr.andromeda.auth.batch.errors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.repositories.ErrorRepository;
import fr.andromeda.api.services.interfaces.IErrorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class ErrorPopulator implements ApplicationRunner {

    @Value("classpath:${resources.application.errors.file:/errors.json}")
    private Resource errorFile;

    private final IErrorService errorService;
    private final ErrorRepository errorRepository;
    private final ObjectMapper objectMapper;

    public ErrorPopulator(IErrorService errorService,
                          ErrorRepository errorRepository,
                          ObjectMapper objectMapper) {
        this.errorService = errorService;
        this.errorRepository = errorRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        errorRepository.deleteAll();
        try (InputStream is = errorFile.getInputStream()) {
            List<ErrorDTO> errors = objectMapper.readValue(is, new TypeReference<>() {});
            if (!errors.isEmpty()) {
                errorService.createAll(errors);
            }
        }
    }

}
