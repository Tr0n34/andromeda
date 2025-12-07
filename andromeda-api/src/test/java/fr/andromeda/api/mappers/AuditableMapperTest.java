package fr.andromeda.api.mappers;

import fr.andromeda.api.dto.IDTO;
import fr.andromeda.api.entities.IEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class AuditableMapperTest {

    static class TestDTO implements IDTO {
        private String field;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }

    static class TestEntity implements IEntity {
        private Long id;
        private String createdOn;
        private String updatedOn;

        @Override
        public Long getId() {
            return id;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public String getUpdatedOn() {
            return updatedOn;
        }

        public void setUpdatedOn(String updatedOn) {
            this.updatedOn = updatedOn;
        }
    }

    @Mapper
    interface TestMapper extends IAuditableMapper<TestDTO, TestEntity> {
        @Override
        @Mapping(target = "createdOn", ignore = true)
        @Mapping(target = "updatedOn", ignore = true)
        TestEntity toEntity(TestDTO dto);
    }

    private final TestMapper mapper = Mappers.getMapper(TestMapper.class);

    @Test
    void testToEntity() {
        TestDTO dto = new TestDTO();
        dto.setField("value");
        TestEntity entity = mapper.toEntity(dto);
        assertThat(entity).isNotNull();
        assertThat(entity.getCreatedOn()).isNull();
        assertThat(entity.getUpdatedOn()).isNull();
    }

    @Test
    void testToDto() {
        TestEntity entity = new TestEntity();
        entity.setCreatedOn("created");
        entity.setUpdatedOn("updated");
        TestDTO dto = mapper.toDto(entity);
        assertThat(dto).isNotNull();
    }

}
