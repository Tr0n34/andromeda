package fr.andromeda.cyb.services.impl;

import fr.andromeda.api.errors.ErrorProvider;
import fr.andromeda.api.services.interfaces.AbstractCrudService;
import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.entites.Role;
import fr.andromeda.cyb.mappers.RoleMapper;
import fr.andromeda.cyb.repositories.RoleRepository;
import fr.andromeda.cyb.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractCrudService<RoleDTO, Role, RoleRepository, Long> implements IRoleService {

    @Autowired
    public RoleService(RoleMapper mapper, RoleRepository repository, ErrorProvider errorProvider) {
        super(mapper, repository, RoleService.class.getSimpleName(), errorProvider);
    }

}
