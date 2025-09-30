package fr.andromeda.cyb.services.interfaces;

import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IRoleService {

    void createAll(List<RoleDTO> roleDTOs);

}
