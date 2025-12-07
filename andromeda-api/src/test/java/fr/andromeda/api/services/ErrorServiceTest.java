package fr.andromeda.api.services;

import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.entities.errors.Error;
import fr.andromeda.api.errors.ErrorProvider;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.mappers.ErrorMapper;
import fr.andromeda.api.repositories.ErrorRepository;
import fr.andromeda.api.services.impl.ErrorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ErrorServiceTest {

    @Mock
    private ErrorMapper mapper;

    @Mock
    private ErrorRepository repository;

    @Mock
    private ErrorProvider errorProvider;

    @InjectMocks
    private ErrorService service;

    @Test
    void findByCode_shouldReturnDto_whenFound() throws Exception {
        String code = "ERR001";

        Error entity = new Error();
        entity.setCode(code);

        ErrorDTO dto = new ErrorDTO();
        dto.setCode(code);

        when(repository.findByCode(code)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        ErrorDTO result = service.findByCode(code);

        assertNotNull(result);
        assertEquals(code, result.getCode());

        verify(repository).findByCode(code);
        verify(mapper).toDto(entity);
    }

    @Test
    void findByCode_shouldThrowNotFound_whenMissing() {
        String code = "UNKNOWN";

        when(repository.findByCode(code)).thenReturn(Optional.empty());
        when(errorProvider.notFound(Error.class.getSimpleName()))
                .thenReturn(new ResourceNotFoundException("BS_RESOURCE_NOT_FOUND", "error not found", HttpStatus.NOT_FOUND, "Error"));

        assertThrows(ResourceNotFoundException.class, () -> service.findByCode(code));

        verify(repository).findByCode(code);
        verify(mapper, never()).toDto(any());
    }

    @Test
    void findByHttpStatus_shouldReturnNull_untilImplemented() {
        assertNull(service.findByHttpStatus(HttpStatus.BAD_REQUEST));
    }

    @Test
    void findAllByStatusAndEntityName_shouldReturnList_whenFound() throws Exception {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String entityName = "User";

        Error entity = new Error();
        ErrorDTO dto = new ErrorDTO();

        List<Error> entityList = List.of(entity);
        List<ErrorDTO> dtoList = List.of(dto);

        when(repository.findAllByStatusAndEntityName(status, entityName)).thenReturn(entityList);
        when(mapper.toDtoList(entityList)).thenReturn(dtoList);

        List<ErrorDTO> result = service.findAllByStatusAndEntityName(status, entityName);

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(repository).findAllByStatusAndEntityName(status, entityName);
        verify(mapper).toDtoList(entityList);
    }

    @Test
    void findAllByStatusAndEntityName_shouldThrowNotFound_whenEmpty() {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String entityName = "Error";

        when(repository.findAllByStatusAndEntityName(status, entityName)).thenReturn(new ArrayList<>());
        when(errorProvider.notFound(Error.class.getSimpleName()))
                .thenReturn(new ResourceNotFoundException("BS_RESOURCE_NOT_FOUND", "error not found", HttpStatus.NOT_FOUND, "Error"));

        assertThrows(ResourceNotFoundException.class, () -> service.findAllByStatusAndEntityName(status, entityName));

        verify(repository).findAllByStatusAndEntityName(status, entityName);
        verify(mapper, never()).toDtoList(any());
    }

}
