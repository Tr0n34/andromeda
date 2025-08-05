package fr.andromeda.sport.services.impl;

import fr.andromeda.sport.mappers.RawDataMapper;
import fr.andromeda.sport.objects.dto.RawDataDTO;
import fr.andromeda.sport.objects.inputs.RawDataInput;
import fr.andromeda.sport.repositories.RawDataRepository;
import fr.andromeda.sport.services.RawDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawDataServiceImpl implements RawDataService {

    private static final Logger logger = LoggerFactory.getLogger(RawDataServiceImpl.class);

    private RawDataRepository rawDataRepository;
    private RawDataMapper rawDataMapper;

    @Autowired
    public RawDataServiceImpl(RawDataRepository rawDataRepository, RawDataMapper rawDataMapper) {
        this.rawDataRepository = rawDataRepository;
        this.rawDataMapper = rawDataMapper;
    }

    @Override
    public Long create(RawDataDTO rawDataDTO) {
        rawDataRepository.save(rawDataMapper.toEntity(rawDataDTO));
        return 1L;
    }

    @Override
    public RawDataDTO read(Long id) {
        return null;
    }

    @Override
    public List<RawDataDTO> search(RawDataInput criteria) {
        return List.of();
    }

    public List<RawDataDTO> search(RawDataDTO criteria) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
