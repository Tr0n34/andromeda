package fr.andromeda.cyb.mappers;

import fr.andromeda.api.mappers.IAuditableMapper;
import fr.andromeda.cyb.dto.ClientDTO;
import fr.andromeda.cyb.entites.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends IAuditableMapper<ClientDTO, Client> {

}
