package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.ClientDTO;
import fr.andromeda.cyb.entites.Client;
import fr.andromeda.cyb.mappers.interfaces.IMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends IMapper<ClientDTO, Client> {

}
