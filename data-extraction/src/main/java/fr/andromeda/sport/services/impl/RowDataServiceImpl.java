package fr.andromeda.sport.services.impl;

import fr.andromeda.sport.objects.dto.RowDataDTO;
import fr.andromeda.sport.objects.inputs.RowDataInput;
import fr.andromeda.sport.services.RowDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RowDataServiceImpl implements RowDataService {

    private static final Logger logger = LoggerFactory.getLogger(RowDataService.class);
    private static Long DEFAULT_ID = 0L;

    @Override
    public Long create(RowDataDTO rowDataDTO) {
        DEFAULT_ID = DEFAULT_ID + 1;
        return DEFAULT_ID;
    }

    @Override
    public RowDataDTO read(Long id) {
        return null;
    }

    @Override
    public List<RowDataDTO> search(RowDataInput criteria) {
        return List.of();
    }

    public List<RowDataDTO> search(RowDataDTO criteria) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
