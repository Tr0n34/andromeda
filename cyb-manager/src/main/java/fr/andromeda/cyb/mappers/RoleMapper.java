package fr.andromeda.cyb.mappers;

import fr.andromeda.api.mappers.IAuditableMapper;
import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.entites.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends IAuditableMapper<RoleDTO, Role> {

}
