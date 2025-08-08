package fr.andromeda.sport.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.andromeda.sport.dto.TrainingDTO;
import fr.andromeda.sport.enums.TrainingStatus;
import fr.andromeda.sport.services.TrainingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TrainingController.class)
@TestPropertySource(properties = "api.prefix=/api/v1")
class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TrainingService trainingService;

    @Test
    void start_shouldCreateTrainingAndReturnLocationHeader() throws Exception {
        long expectedId = 123L;
        String deviceId = "abc123";
        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setDeviceId(deviceId);

        when(trainingService.start(deviceId)).thenReturn(expectedId);

        mockMvc.perform(post("/api/v1/trainings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trainingDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/trainings/123"))
                .andExpect(content().string("123"));

        verify(trainingService).start("abc123");
    }

    @Test
    void get_shouldReturnTrainingById() throws Exception {
        long id = 1L;
        TrainingDTO dto = new TrainingDTO()
                .setId(id)
                .setDeviceId("abc123")
                .setStartedOn(LocalDateTime.now())
                .setStatus(TrainingStatus.STARTED);

        when(trainingService.findTraining(id)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/trainings/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));

        verify(trainingService).findTraining(id);
    }

}
