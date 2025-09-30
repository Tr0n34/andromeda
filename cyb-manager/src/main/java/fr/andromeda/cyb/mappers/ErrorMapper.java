package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.entites.errors.Error;
import fr.andromeda.cyb.mappers.interfaces.IMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ErrorMapper extends IMapper<ErrorDTO, Error> {

}
