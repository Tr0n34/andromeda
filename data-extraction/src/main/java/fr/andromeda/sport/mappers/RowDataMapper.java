package fr.andromeda.sport.mappers;


import fr.andromeda.sport.dto.RowDataDTO;
import fr.andromeda.sport.entities.RowDataEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RowDataMapper extends IMapper<RowDataDTO, RowDataEntity> {

}
