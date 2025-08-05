package fr.andromeda.sport.services;

import fr.andromeda.sport.objects.dto.RawDataDTO;
import fr.andromeda.sport.objects.inputs.RawDataInput;

import java.util.List;

public interface RawDataService {

    Long create(RawDataDTO rawDataDTO);

    RawDataDTO read(Long id);

    List<RawDataDTO> search(RawDataInput criteria);

    void delete(Long id);

}
