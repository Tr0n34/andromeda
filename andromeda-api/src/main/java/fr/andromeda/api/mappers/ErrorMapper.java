package fr.andromeda.api.mappers;

import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.entities.errors.Error;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ErrorMapper extends IAuditableMapper<ErrorDTO, Error> {


}
