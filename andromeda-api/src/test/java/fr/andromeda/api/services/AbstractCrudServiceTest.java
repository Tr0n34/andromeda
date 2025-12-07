package fr.andromeda.api.services;

import fr.andromeda.api.dto.IDTO;
import fr.andromeda.api.entities.IEntity;
import fr.andromeda.api.errors.ErrorProvider;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.mappers.IMapper;
import fr.andromeda.api.services.interfaces.AbstractCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import fr.andromeda.api.dto.IDTO;
import fr.andromeda.api.entities.IEntity;
import fr.andromeda.api.errors.ErrorProvider;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.mappers.IMapper;
import fr.andromeda.api.services.interfaces.AbstractCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class AbstractCrudServiceTest {

    interface TestDTO extends IDTO {}
    interface TestEntity extends IEntity {}
    interface TestRepository extends JpaRepository<TestEntity, Long> {}

    static class TestService extends AbstractCrudService<TestDTO, TestEntity, TestRepository, Long> {
        public TestService(IMapper<TestDTO, TestEntity> mapper, TestRepository repository, ErrorProvider errorProvider) {
            super(mapper, repository, "TestEntity", errorProvider);
        }
    }

    @Mock TestRepository repository;
    @Mock IMapper<TestDTO, TestEntity> mapper;
    @Mock ErrorProvider errorProvider;
    @Mock TestEntity entity;
    @Mock TestDTO dto;

    @InjectMocks TestService service;

    private ResourceNotFoundException notFoundException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notFoundException = new ResourceNotFoundException(
                "BS_RESOURCE_NOT_FOUND", "TestEntity not found", HttpStatus.NOT_FOUND, "TestEntity"
        );
    }

    // ------------------- get / loadEntity -------------------

    @Test
    void testGet_success() throws ResourceNotFoundException {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        var result = service.get(1L);

        assertThat(result).isEqualTo(dto);
        verify(repository).findById(1L);
        verify(mapper).toDto(entity);
        verifyNoMoreInteractions(repository, mapper, errorProvider);
    }

    @Test
    void testGet_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        when(errorProvider.notFound("TestEntity")).thenReturn(notFoundException);

        assertThatThrownBy(() -> service.get(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("TestEntity not found");

        verify(repository).findById(1L);
        verify(errorProvider).notFound("TestEntity");
        verifyNoMoreInteractions(repository, mapper, errorProvider);
    }

    @Test
    void testLoadEntity_success() throws ResourceNotFoundException {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.loadEntity(1L);
        assertThat(result).isEqualTo(entity);
    }

    @Test
    void testLoadEntity_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        when(errorProvider.notFound("TestEntity")).thenReturn(notFoundException);

        assertThatThrownBy(() -> service.loadEntity(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("TestEntity not found");
    }

    // ------------------- create / update -------------------

    @Test
    void testCreate_success() throws ResourceNotFoundException {
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        var result = service.create(dto);

        assertThat(result).isEqualTo(dto);
        verify(repository).save(entity);
        verify(mapper).toDto(entity);
        verify(mapper).toEntity(dto);
        verifyNoMoreInteractions(repository, mapper, errorProvider);
    }

    @Test
    void testUpdate_success() throws ResourceNotFoundException {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        var result = service.update(1L, dto);

        assertThat(result).isEqualTo(dto);
        verify(repository).findById(1L);
        verify(repository).save(entity);
        verify(mapper).toEntity(dto);
        verify(mapper).toDto(entity);
        verifyNoMoreInteractions(repository, mapper, errorProvider);
    }

    // ------------------- delete -------------------

    @Test
    void testDelete_success() throws ResourceNotFoundException {
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void testDelete_notFound() {
        doThrow(new org.springframework.dao.EmptyResultDataAccessException(1))
                .when(repository).deleteById(1L);
        when(errorProvider.notFound("TestEntity")).thenReturn(notFoundException);

        assertThatThrownBy(() -> service.delete(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("TestEntity not found");
    }

    // ------------------- findAll -------------------

    @Test
    void testFindAll() {
        var entities = List.of(entity);
        when(repository.findAll()).thenReturn(entities);
        when(mapper.toDtoList(entities)).thenReturn(List.of(dto));

        var result = service.findAll();

        assertThat(result).containsExactly(dto);
        verify(repository).findAll();
        verify(mapper).toDtoList(entities);
        verifyNoMoreInteractions(repository, mapper, errorProvider);
    }

    // ------------------- patch -------------------

    @Test
    void testPatch_success() throws ResourceNotFoundException {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);

        service.patch(1L, dto);

        verify(mapper).patchFromDto(dto, entity);
        verify(repository).save(entity);
    }

    @Test
    void testPatch_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        when(errorProvider.notFound("TestEntity")).thenReturn(notFoundException);

        assertThatThrownBy(() -> service.patch(1L, dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("TestEntity not found");
    }

    // ------------------- createAll -------------------

    @Test
    void testCreateAll() {
        var dtos = List.of(dto, dto);
        var entities = List.of(entity, entity);

        when(mapper.toEntity(dto)).thenReturn(entity);

        service.createAll(dtos);

        verify(mapper, times(dtos.size())).toEntity(dto);
        verify(repository).saveAll(entities);
        verifyNoMoreInteractions(repository, mapper, errorProvider);
    }

}