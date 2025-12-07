package fr.andromeda.api.mappers;

import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.entities.errors.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ErrorMapperTest {

    private ErrorMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(ErrorMapper.class);
    }

    @Test
    void testEntityToDto() {
        Error error = new Error()
                .setId(1L)
                .setCode("ERR001")
                .setMessage("Bad request")
                .setEntityName("User");
        ErrorDTO dto = mapper.toDto(error);
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(error.getId());
        assertThat(dto.getCode()).isEqualTo(error.getCode());
        assertThat(dto.getMessage()).isEqualTo(error.getMessage());
        assertThat(dto.getEntityName()).isEqualTo(error.getEntityName());
    }

    @Test
    void testDtoToEntity() {
        ErrorDTO dto = new ErrorDTO();
        dto.setId(2L);
        dto.setCode("ERR002");
        dto.setMessage("Invalid field");
        dto.setEntityName("Product");
        Error entity = mapper.toEntity(dto);
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(dto.getId());
        assertThat(entity.getCode()).isEqualTo(dto.getCode());
        assertThat(entity.getMessage()).isEqualTo(dto.getMessage());
        assertThat(entity.getEntityName()).isEqualTo(dto.getEntityName());
    }

    @Test
    void testToDtoList() {
        Error error1 = new Error().setCode("ERR001");
        Error error2 = new Error().setCode("ERR002");
        var dtos = mapper.toDtoList(List.of(error1, error2));
        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getCode()).isEqualTo("ERR001");
        assertThat(dtos.get(1).getCode()).isEqualTo("ERR002");
    }

    @Test
    void testEntityToDto_null() {
        assertThat(mapper.toDto(null)).isNull();
    }

    @Test
    void testDtoToEntity_null() {
        assertThat(mapper.toEntity(null)).isNull();
    }

    @Test
    void testToDtoList_null() {
        assertThat(mapper.toDtoList(null)).isNull();
    }

    @Test
    void testToDtoList_empty() {
        List<ErrorDTO> result = mapper.toDtoList(List.of());
        assertThat(result).isEmpty();
    }

    @Test
    void testToEntityList_null() {
        assertThat(mapper.toEntityList(null)).isNull();
    }

    @Test
    void testToEntityList_empty() {
        List<Error> result = mapper.toEntityList(List.of());
        assertThat(result).isEmpty();
    }

    @Test
    void testPatchFromDto_nullDto() {
        Error entity = new Error().setCode("ERR001");
        Error result = mapper.patchFromDto(null, entity);
        assertThat(result).isSameAs(entity);
        assertThat(result.getCode()).isEqualTo("ERR001");
    }

    @Test
    void testPatchFromDto_nullEntity_throwsException() {
        ErrorDTO dto = new ErrorDTO();
        dto.setCode("ERR003");
        assertThrows(NullPointerException.class, () -> mapper.patchFromDto(dto, null));
    }


}
