package fr.andromeda.cyb.mappers;

import fr.andromeda.api.mappers.IAuditableMapper;
import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.entites.errors.Error;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ErrorMapper extends IAuditableMapper<ErrorDTO, Error> {

}
