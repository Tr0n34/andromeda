package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.authentification.RefreshTokenDTO;
import fr.andromeda.cyb.entites.authentication.RefreshToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper extends IMapper<RefreshTokenDTO, RefreshToken> {

}
