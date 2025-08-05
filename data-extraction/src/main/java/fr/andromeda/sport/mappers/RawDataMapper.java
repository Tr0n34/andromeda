package fr.andromeda.sport.mappers;

import fr.andromeda.sport.entities.RawDataEntity;
import fr.andromeda.sport.objects.dto.RawDataDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RawDataMapper {

    RawDataDTO toDto(RawDataEntity entity);
    RawDataEntity toEntity(RawDataDTO dto);

}
