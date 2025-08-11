package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.ClientDTO;
import fr.andromeda.cyb.entites.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends IMapper<ClientDTO, ClientEntity>{

}
