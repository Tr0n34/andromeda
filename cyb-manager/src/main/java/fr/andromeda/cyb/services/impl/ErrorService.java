package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.configurations.errors.ErrorProvider;
import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.entites.errors.Error;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.mappers.ErrorMapper;
import fr.andromeda.cyb.repositories.ErrorRepository;
import fr.andromeda.cyb.services.AbstractCrudService;
import fr.andromeda.cyb.services.interfaces.IErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ErrorService extends AbstractCrudService<ErrorDTO, Error, ErrorRepository, Long>  implements IErrorService {

    @Autowired
    public ErrorService(ErrorMapper errorMapper, ErrorRepository errorRepository) {
        super(errorMapper, errorRepository, Error.class);
    }

    @Override
    public ErrorDTO findByCode(String code) throws ResourceNotFoundException {
        return getMapper().toDto(getRepository().findByCode(code)
                .orElseThrow(() -> getErrorProvider().notFound(Error.class)));
    }

    @Override
    public void createAll(List<ErrorDTO> errorDTOs) {
        getRepository().saveAll(errorDTOs.stream()
            .map(getMapper()::toEntity)
            .toList());
    }

    @Override
    public ErrorDTO findByHttpStatus(HttpStatus status) {
        return null;
    }

    @Override
    public Long add(ErrorDTO errorDTO) {
        return 1L;
    }


}
