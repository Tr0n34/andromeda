package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.entites.Role;
import fr.andromeda.cyb.mappers.interfaces.IMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends IMapper<RoleDTO, Role> {

}
