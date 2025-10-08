package fr.andromeda.cyb.services.interfaces;

import fr.andromeda.api.services.interfaces.ICrudService;
import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.entites.Role;
import fr.andromeda.cyb.repositories.RoleRepository;

import java.util.List;

public interface IRoleService extends ICrudService<RoleDTO, Role, RoleRepository, Long> {

    void createAll(List<RoleDTO> roleDTOs);

}
