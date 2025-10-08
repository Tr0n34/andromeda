package fr.andromeda.cyb.mappers;

import fr.andromeda.api.mappers.IAuditableMapper;
import fr.andromeda.cyb.dto.authentication.RefreshTokenDTO;
import fr.andromeda.cyb.entites.auth.RefreshToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper extends IAuditableMapper<RefreshTokenDTO, RefreshToken> {

}
