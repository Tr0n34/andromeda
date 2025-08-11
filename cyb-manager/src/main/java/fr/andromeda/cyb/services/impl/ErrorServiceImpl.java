package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.mappers.ErrorMapper;
import fr.andromeda.cyb.repositories.ErrorRepository;
import fr.andromeda.cyb.services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorServiceImpl implements ErrorService {

    private final ErrorRepository errorRepository;
    private final ErrorMapper errorMapper;

    @Autowired
    public ErrorServiceImpl(ErrorRepository errorRepository, ErrorMapper errorMapper) {
        this.errorRepository = errorRepository;
        this.errorMapper = errorMapper;
    }

    @Override
    public ErrorDTO find(Long id) {
        return null;
    }

    @Override
    public List<ErrorDTO> findAll() {
        return List.of();
    }

    @Override
    public ErrorDTO findByCode(String code) {
        return null;
    }

    @Override
    public ErrorDTO findByHttpStatus(HttpStatus status) {
        return null;
    }

    @Override
    public Long add(ErrorDTO errorDTO) {
        return errorRepository.save(errorMapper.toEntity(errorDTO)).getId();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ErrorDTO update(ErrorDTO errorDTO) {
        return null;
    }

}
