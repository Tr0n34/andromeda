package fr.andromeda.sport.mappers;

import fr.andromeda.sport.entities.TrainingEntity;
import fr.andromeda.sport.dto.TrainingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingMapper extends IMapper<TrainingDTO, TrainingEntity> {

}
