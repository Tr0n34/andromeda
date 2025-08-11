package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.entites.ErrorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ErrorMapper extends IMapper<ErrorDTO, ErrorEntity> {

}
