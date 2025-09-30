package fr.andromeda.cyb.services.business;

import fr.andromeda.cyb.configurations.errors.ErrorProvider;
import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.entites.Role;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserValidatorService {

    private final ErrorProvider errorProvider;
    private final RoleRepository roleRepository;

    @Autowired
    public UserValidatorService(RoleRepository roleRepository, ErrorProvider errorProvider) {
        this.roleRepository = roleRepository;
        this.errorProvider = errorProvider;
    }

    public void validateAuthoritiesExist(Set<RoleDTO> roleDTOs, List<RoleDTO> roles) throws ResourceNotFoundException {
        if (roleDTOs == null || roleDTOs.isEmpty()) {
            throw errorProvider.notFound("ROLE");
        }
        Set<String> validAuthorities = roles.stream()
                .map(RoleDTO::getAuthority)
                .collect(Collectors.toSet());

        roleDTOs.stream()
                .map(RoleDTO::getAuthority)
                .filter(validAuthorities::contains)
                .findAny()
                .orElseThrow(() -> errorProvider.notFound("ROLE"));

    }

}
