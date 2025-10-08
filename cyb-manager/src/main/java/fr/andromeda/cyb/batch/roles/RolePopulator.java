package fr.andromeda.cyb.batch.roles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.repositories.RoleRepository;
import fr.andromeda.cyb.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class RolePopulator implements ApplicationRunner {

    @Value("classpath:${resources.application.roles.file:/roles.json}")
    private Resource roleFile;

    private final RoleRepository roleRepository;
    private final IRoleService roleService;
    private final ObjectMapper objectMapper;

    public RolePopulator(RoleRepository roleRepository, ObjectMapper objectMapper, IRoleService roleService) {
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
        this.roleService = roleService;

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleRepository.deleteAll();
        try (InputStream is = roleFile.getInputStream()) {
            List<RoleDTO> roles = objectMapper.readValue(is, new TypeReference<>() {});
            if (!roles.isEmpty()) {
                roleService.createAll(roles);
            }
        }
    }

}
