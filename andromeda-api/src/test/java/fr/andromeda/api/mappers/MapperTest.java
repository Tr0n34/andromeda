package fr.andromeda.api.mappers;

import fr.andromeda.api.dto.IDTO;
import fr.andromeda.api.entities.IEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MapperTest {

    static class TestDTO implements IDTO {
        private Long id;
        private String field;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
    }

    static class TestEntity implements IEntity {
        private Long id;
        private String field;
        private String ignoredField;

        @Override
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getField() { return field; }
        public void setField(String field) { this.field = field; }

        public String getIgnoredField() { return ignoredField; }
        public void setIgnoredField(String ignoredField) { this.ignoredField = ignoredField; }
    }

    @Mapper
    interface TestMapper extends IMapper<TestDTO, TestEntity> {

        @Override
        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        TestEntity patchFromDto(TestDTO dto, @MappingTarget TestEntity entity);

    }

    private final TestMapper mapper = Mappers.getMapper(TestMapper.class);

    @Test
    void testToEntity() {
        TestDTO dto = new TestDTO();
        dto.setId(1L);
        dto.setField("value");
        TestEntity entity = mapper.toEntity(dto);
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getField()).isEqualTo("value");
    }

    @Test
    void testToDto() {
        TestEntity entity = new TestEntity();
        entity.setId(2L);
        entity.setField("value");
        TestDTO dto = mapper.toDto(entity);
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(2L);
        assertThat(dto.getField()).isEqualTo("value");
    }

    @Test
    void testToDtoList() {
        TestEntity e1 = new TestEntity(); e1.setId(1L); e1.setField("a");
        TestEntity e2 = new TestEntity(); e2.setId(2L); e2.setField("b");
        List<TestDTO> dtos = mapper.toDtoList(List.of(e1, e2));
        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getId()).isEqualTo(1L);
        assertThat(dtos.get(0).getField()).isEqualTo("a");
        assertThat(dtos.get(1).getId()).isEqualTo(2L);
        assertThat(dtos.get(1).getField()).isEqualTo("b");
    }

    @Test
    void testToEntityList() {
        TestDTO d1 = new TestDTO(); d1.setId(10L); d1.setField("x");
        TestDTO d2 = new TestDTO(); d2.setId(20L); d2.setField("y");
        List<TestEntity> entities = mapper.toEntityList(List.of(d1, d2));
        assertThat(entities).hasSize(2);
        assertThat(entities.get(0).getId()).isEqualTo(10L);
        assertThat(entities.get(0).getField()).isEqualTo("x");
        assertThat(entities.get(1).getId()).isEqualTo(20L);
        assertThat(entities.get(1).getField()).isEqualTo("y");
    }

    @Test
    void testPatchFromDto() {
        TestDTO dto = new TestDTO();
        dto.setField("patched");
        TestEntity entity = new TestEntity();
        entity.setId(1L);
        entity.setField("original");
        entity.setIgnoredField("keep");
        mapper.patchFromDto(dto, entity);
        assertThat(entity.getField()).isEqualTo("patched");
        assertThat(entity.getIgnoredField()).isEqualTo("keep");
    }

    @Test
    void testToEntity_null() {
        assertThat(mapper.toEntity(null)).isNull();
    }

    @Test
    void testToDto_null() {
        assertThat(mapper.toDto(null)).isNull();
    }

    @Test
    void testToDtoList_null() {
        assertThat(mapper.toDtoList(null)).isNull();
    }

    @Test
    void testToEntityList_null() {
        assertThat(mapper.toEntityList(null)).isNull();
    }

    @Test
    void testPatchFromDto_nullDto_doesNothing() {
        TestEntity entity = new TestEntity();
        entity.setField("original");
        entity.setIgnoredField("keep");
        mapper.patchFromDto(null, entity);
        assertThat(entity.getField()).isEqualTo("original");
        assertThat(entity.getIgnoredField()).isEqualTo("keep");
    }

    @Test
    void testPatchFromDto_nullEntity_throwsException() {
        TestDTO dto = new TestDTO();
        dto.setField("value");
        assertThrows(NullPointerException.class, () -> mapper.patchFromDto(dto, null));
    }

    @Test
    void testPatchFromDto_bothNull() {
        assertThat(mapper.patchFromDto(null, null)).isNull();
    }
}
