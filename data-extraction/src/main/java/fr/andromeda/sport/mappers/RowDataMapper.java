package fr.andromeda.sport.mappers;

import fr.andromeda.sport.entities.RowDataEntity;
import fr.andromeda.sport.entities.TrainingEntity;
import fr.andromeda.sport.objects.dto.RowDataDTO;
import fr.andromeda.sport.objects.dto.TrainingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RowDataMapper {

    RowDataDTO toDto(RowDataEntity entity);
    RowDataEntity toEntity(RowDataDTO dto);

}
