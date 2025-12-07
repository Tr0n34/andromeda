package fr.andromeda.cyb.controllers;


import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.services.impl.ErrorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ErrorController.class)
public class ErrorControllerTest {



        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private ErrorService errorService;

        private static final String BASE = "/api/v1/errors";

        @Test
        void createError_shouldReturnCreated() throws Exception {
            ErrorDTO saved = new ErrorDTO();
            saved.setId(42L);

            Mockito.when(errorService.create(Mockito.any(ErrorDTO.class))).thenReturn(saved);

            mockMvc.perform(post(BASE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"message\":\"test\"}"))
                    .andExpect(status().isCreated())
                    .andExpect(header().string("Location", BASE + "/42"));
        }

        @Test
        void createErrors_batchShouldReturnNoContent() throws Exception {
            mockMvc.perform(post(BASE + "/batch")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("[{\"message\":\"err1\"}, {\"message\":\"err2\"}]"))
                    .andExpect(status().isNoContent());

            Mockito.verify(errorService).createAll(any());
        }

        @Test
        void getAllErrors_shouldReturnList() throws Exception {
            ErrorDTO e1 = new ErrorDTO();
            e1.setId(1L);
            ErrorDTO e2 = new ErrorDTO();
            e2.setId(2L);

            Mockito.when(errorService.findAll()).thenReturn(List.of(e1, e2));

            mockMvc.perform(get(BASE))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1L))
                    .andExpect(jsonPath("$[1].id").value(2L));
        }

        @Test
        void getError_shouldReturnSingleError() throws Exception {
            ErrorDTO error = new ErrorDTO();
            error.setId(5L);

            Mockito.when(errorService.get(5L)).thenReturn(error);

            mockMvc.perform(get(BASE + "/5"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(5L));
        }

        @Test
        void updateError_shouldReturnNoContent() throws Exception {
            mockMvc.perform(patch(BASE + "/7")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"message\":\"updated\"}"))
                    .andExpect(status().isNoContent());

            Mockito.verify(errorService).update(eq(7L), any(ErrorDTO.class));
        }

        @Test
        void deleteError_shouldReturnNoContent() throws Exception {
            mockMvc.perform(delete(BASE + "/9"))
                    .andExpect(status().isNoContent());

            Mockito.verify(errorService).delete(9L);
        }

/*
        @Test
        void getError_shouldReturn404_whenNotFound() throws Exception {
            Mockito.when(errorService.get(123L)).thenThrow(new ResourceNotFoundException());

            mockMvc.perform(get(BASE + "/123"))
                    .andExpect(status().isNotFound());
        }
    }
*/

}
