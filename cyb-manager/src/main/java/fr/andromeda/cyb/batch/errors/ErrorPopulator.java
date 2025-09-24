package fr.andromeda.cyb.batch.errors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.repositories.ErrorRepository;
import fr.andromeda.cyb.services.impl.ErrorService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ErrorPopulator implements ApplicationRunner {

    private final ErrorService errorService;
    private final ErrorRepository errorRepository;
    private final ObjectMapper objectMapper;

    public ErrorPopulator(ErrorService errorService,
                              ErrorRepository errorRepository,
                              ObjectMapper objectMapper) {
        this.errorService = errorService;
        this.errorRepository = errorRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        errorRepository.deleteAll();
        try (InputStream is = getClass().getResourceAsStream("/errors.json")) {
            List<ErrorDTO> errors = objectMapper.readValue(is, new TypeReference<List<ErrorDTO>>() {});
            if (!errors.isEmpty()) {
                LocalDateTime now = LocalDateTime.now();
                errors.forEach(dto -> {
                    dto.setCreatedOn(now);
                    dto.setUpdatedOn(now);
                });
                errorService.createAll(errors);
            }
        }
    }

}
