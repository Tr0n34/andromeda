package fr.andromeda.cyb.mappers;

import fr.andromeda.api.mappers.IMapper;
import fr.andromeda.cyb.dto.ApplicationParameterDTO;
import fr.andromeda.cyb.entites.application.ApplicationParameter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationParameterMapper extends IMapper<ApplicationParameterDTO, ApplicationParameter> {

}
