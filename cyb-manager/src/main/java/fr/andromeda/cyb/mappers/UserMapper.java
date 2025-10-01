package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.entites.User;
import fr.andromeda.cyb.mappers.interfaces.IMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper extends IMapper<UserDTO, User> {

    @Override
    @Mapping(target = "roles", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User patchFromDto(UserDTO dto, @MappingTarget User entity);

}
