package fr.andromeda.sport.services;

import fr.andromeda.sport.dto.RowDataDTO;
import fr.andromeda.sport.inputs.RowDataInput;

import java.util.List;

public interface RowDataService {

    Long create(RowDataDTO rawDataDTO);

    RowDataDTO read(Long id);

    List<RowDataDTO> search(RowDataInput criteria);

    void delete(Long id);

}
