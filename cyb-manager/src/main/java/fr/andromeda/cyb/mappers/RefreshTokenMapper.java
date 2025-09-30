package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.authentication.RefreshTokenDTO;
import fr.andromeda.cyb.entites.auth.RefreshToken;
import fr.andromeda.cyb.mappers.interfaces.IMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper extends IMapper<RefreshTokenDTO, RefreshToken> {

}
