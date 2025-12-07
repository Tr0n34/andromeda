package fr.andromeda.api;

import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.errors.impl.ErrorProviderImpl;
import fr.andromeda.api.exceptions.BusinessException;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.services.interfaces.IErrorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class ErrorProviderTest {

    @Mock
    private IErrorService errorService;

    @InjectMocks
    private ErrorProviderImpl errorProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNotFound_success() throws ResourceNotFoundException {
        ErrorDTO dto = new ErrorDTO();
        dto.setCode("ERR-404");
        dto.setStatus(HttpStatus.NOT_FOUND);
        dto.setMessage("Entity %s not found");

        when(errorService.findByCode("BS_RESOURCE_NOT_FOUND")).thenReturn(dto);

        ResourceNotFoundException ex = errorProvider.notFound("User");

        assertThat(ex).isNotNull();
        assertThat(ex.getCode()).isEqualTo("ERR-404");
        assertThat(ex.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(ex.getMessage()).contains("User");

        verify(errorService).findByCode("BS_RESOURCE_NOT_FOUND");
        verifyNoMoreInteractions(errorService);
    }

    @Test
    void testNotFound_errorServiceThrows_exceptionWrapped() throws ResourceNotFoundException {
        when(errorService.findByCode("BS_RESOURCE_NOT_FOUND"))
                .thenThrow(new ResourceNotFoundException("X", "msg", HttpStatus.NOT_FOUND, "Error"));

        assertThatThrownBy(() -> errorProvider.notFound("User"))
                .isInstanceOf(RuntimeException.class);

        verify(errorService).findByCode("BS_RESOURCE_NOT_FOUND");
        verifyNoMoreInteractions(errorService);
    }

    @Test
    void testGetException_success() throws ResourceNotFoundException {
        ErrorDTO dto = new ErrorDTO();
        dto.setCode("ERR100");
        dto.setMessage("Test message");
        dto.setStatus(HttpStatus.NOT_FOUND);

        when(errorService.findAllByStatusAndEntityName(HttpStatus.BAD_REQUEST, "User"))
                .thenReturn(List.of(dto));

        BusinessException ex = errorProvider.getException("ERR100", 400, "User");

        assertThat(ex.getCode()).isEqualTo("ERR100");
        assertThat(ex.getMessage()).isEqualTo("Test message");
        assertThat(ex.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);

        verify(errorService).findAllByStatusAndEntityName(HttpStatus.BAD_REQUEST, "User");
        verifyNoMoreInteractions(errorService);
    }

    @Test
    void testGetException_noMatchingError_throwsRuntimeException() throws ResourceNotFoundException {
        when(errorService.findAllByStatusAndEntityName(HttpStatus.BAD_REQUEST, "User"))
                .thenReturn(List.of());

        assertThatThrownBy(() -> errorProvider.getException("ERR100", 400, "User"))
                .isInstanceOf(RuntimeException.class);

        verify(errorService).findAllByStatusAndEntityName(HttpStatus.BAD_REQUEST, "User");
        verifyNoMoreInteractions(errorService);
    }

    @Test
    void testGetException_errorServiceThrows_exceptionWrapped() throws ResourceNotFoundException {
        when(errorService.findAllByStatusAndEntityName(HttpStatus.BAD_REQUEST, "User"))
                .thenThrow(new ResourceNotFoundException("X", "msg", HttpStatus.NOT_FOUND, "Error"));

        assertThatThrownBy(() -> errorProvider.getException("ERR100", 400, "User"))
                .isInstanceOf(RuntimeException.class);

        verify(errorService).findAllByStatusAndEntityName(HttpStatus.BAD_REQUEST, "User");
        verifyNoMoreInteractions(errorService);
    }

}
