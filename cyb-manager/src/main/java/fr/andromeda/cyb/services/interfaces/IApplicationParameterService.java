package fr.andromeda.cyb.services.interfaces;

import fr.andromeda.api.services.interfaces.ICrudService;
import fr.andromeda.cyb.dto.ApplicationParameterDTO;
import fr.andromeda.cyb.entites.application.ApplicationParameter;
import fr.andromeda.cyb.enums.AppParameter;
import fr.andromeda.cyb.repositories.ApplicationConfigurationRepository;

import java.util.List;

public interface IApplicationParameterService  extends ICrudService<ApplicationParameterDTO, ApplicationParameter, ApplicationConfigurationRepository, Long> {

    ApplicationParameterDTO findApplicationConfigurationByKey(String key);

    void createAll(List<ApplicationParameterDTO> applicationParameters);

    void patch(AppParameter appParameter, ApplicationParameterDTO applicationParameterDTO);

}
