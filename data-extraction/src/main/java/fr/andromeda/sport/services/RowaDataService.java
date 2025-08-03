package fr.andromeda.sport.services;

import fr.andromeda.sport.objects.dto.RowDataDTO;
import fr.andromeda.sport.objects.inputs.RowDataInput;

import java.util.List;

public interface RowaDataService {

    Long create(RowDataDTO rowDataDTO);

    RowDataDTO read(Long id);

    List<RowDataDTO> search(RowDataInput criteria);

    void delete(Long id);

}
