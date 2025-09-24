package fr.andromeda.sport.services.impl;

import fr.andromeda.sport.mappers.RowDataMapper;
import fr.andromeda.sport.dto.RowDataDTO;
import fr.andromeda.sport.inputs.RowDataInput;
import fr.andromeda.sport.repositories.RowDataRepository;
import fr.andromeda.sport.services.RowDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RowDataServiceImpl implements RowDataService {

    private static final Logger logger = LoggerFactory.getLogger(RowDataServiceImpl.class);

    private RowDataRepository rowDataRepository;
    private RowDataMapper rowDataMapper;

    @Autowired
    public RowDataServiceImpl(RowDataRepository rowDataRepository, RowDataMapper rowDataMapper) {
        this.rowDataRepository = rowDataRepository;
        this.rowDataMapper = rowDataMapper;
    }

    @Override
    public Long create(RowDataDTO rawDataDTO) {
        rowDataRepository.save(rowDataMapper.toEntity(rawDataDTO));
        return 1L;
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
