package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.configurations.errors.ErrorProvider;
import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.entites.Role;
import fr.andromeda.cyb.mappers.RoleMapper;
import fr.andromeda.cyb.repositories.RoleRepository;
import fr.andromeda.cyb.services.AbstractCrudService;
import fr.andromeda.cyb.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends AbstractCrudService<RoleDTO, Role, RoleRepository, Long> implements IRoleService {

    @Autowired
    public RoleService(RoleMapper mapper, RoleRepository repository, ErrorProvider errorProvider) {
        super(mapper, repository, RoleService.class.getSimpleName(), errorProvider);
    }

    public void createAll(List<RoleDTO> roleDTOs) {
        getRepository().saveAll(roleDTOs.stream()
                .map(getMapper()::toEntity)
                .toList());
    }

    public List<RoleDTO> findAll() {
        return getRepository().findAll().stream()
                .map(getMapper()::toDto)
                .toList();
    }

}
