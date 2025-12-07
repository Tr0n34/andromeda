package fr.andromeda.api.controllers;

import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.services.interfaces.IErrorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ErrorControllerTest {

    private static final String API_CONTEXT_ROOT = "/api/v1";

    private MockMvc mockMvc;

    @Mock
    private IErrorService errorService;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ErrorController(errorService))
                .addPlaceholderValue("api.prefix", API_CONTEXT_ROOT)
                .defaultRequest(post("").with(request -> {
                    request.setRequestURI(API_CONTEXT_ROOT + request.getRequestURI());
                    return request;
                }))
                .build();
    }

    @Test
    void createError_shouldReturn201() throws Exception {
        ErrorDTO dto = new ErrorDTO();
        dto.setId(1L);
        when(errorService.create(any(ErrorDTO.class))).thenReturn(dto);
        mockMvc.perform(post("/errors")
                        .content("{\"message\":\"test error\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void createErrors_shouldReturn204() throws Exception {
        mockMvc.perform(post("/errors/batch")
                        .content("[{\"message\":\"e1\"},{\"message\":\"e2\"}]")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllErrors_shouldReturn200() throws Exception {
        when(errorService.findAll()).thenReturn(List.of(new ErrorDTO()));
        mockMvc.perform(get("/errors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getError_shouldReturn200() throws Exception {
        ErrorDTO dto = new ErrorDTO();
        dto.setId(1L);

        when(errorService.get(1L)).thenReturn(dto);

        mockMvc.perform(get("/errors/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateError_shouldReturn204() throws Exception {
        mockMvc.perform(patch("/errors/1")
                        .content("{\"message\":\"updated\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(errorService).update(eq(1L), any(ErrorDTO.class));
    }

    @Test
    void deleteError_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/errors/1"))
                .andExpect(status().isNoContent());

        verify(errorService).delete(1L);
    }
}
