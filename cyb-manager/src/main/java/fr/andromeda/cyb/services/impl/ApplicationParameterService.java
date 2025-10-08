package fr.andromeda.cyb.services.impl;

import fr.andromeda.api.services.interfaces.AbstractCrudService;
import fr.andromeda.cyb.dto.ApplicationParameterDTO;
import fr.andromeda.cyb.entites.application.ApplicationParameter;
import fr.andromeda.cyb.enums.AppParameter;
import fr.andromeda.cyb.mappers.ApplicationParameterMapper;
import fr.andromeda.cyb.repositories.ApplicationConfigurationRepository;
import fr.andromeda.cyb.services.interfaces.IApplicationParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationParameterService extends AbstractCrudService<ApplicationParameterDTO, ApplicationParameter, ApplicationConfigurationRepository, Long> implements IApplicationParameterService {

    @Autowired
    public ApplicationParameterService(ApplicationParameterMapper mapper,
                                       ApplicationConfigurationRepository repository) {
        super(mapper, repository, ApplicationParameter.class.getSimpleName());
    }

    @Override
    public ApplicationParameterDTO findApplicationConfigurationByKey(String key) {
        return getMapper().toDto(getRepository().findApplicationConfigurationByKey(key)
                .orElseThrow(() -> getErrorProvider().notFound(ApplicationParameter.class.getSimpleName())));
    }

    @Override
    public void patch(AppParameter appParameter, ApplicationParameterDTO applicationParameterDTO) {
        ApplicationParameter applicationParameter = getRepository().findApplicationConfigurationByKey(appParameter.name())
                .orElseThrow(() -> getErrorProvider().notFound(ApplicationParameter.class.getSimpleName()));
        getMapper().patchFromDto(applicationParameterDTO, applicationParameter);
        getRepository().save(applicationParameter);
    }

}
