package fr.andromeda.api.services.impl;

import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.entities.errors.Error;
import fr.andromeda.api.errors.ErrorProvider;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.mappers.ErrorMapper;
import fr.andromeda.api.repositories.ErrorRepository;
import fr.andromeda.api.services.interfaces.AbstractCrudService;
import fr.andromeda.api.services.interfaces.IErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorService extends AbstractCrudService<ErrorDTO, Error, ErrorRepository, Long> implements IErrorService {

    @Autowired
    public ErrorService(ErrorMapper errorMapper, ErrorRepository errorRepository, ErrorProvider errorProvider) {
        super(errorMapper, errorRepository, Error.class.getSimpleName(), errorProvider);
    }

    @Override
    public ErrorDTO findByCode(String code) throws ResourceNotFoundException {
        return getMapper().toDto(getRepository().findByCode(code)
                .orElseThrow(() -> getErrorProvider().notFound(Error.class.getSimpleName())));
    }

    @Override
    public ErrorDTO findByHttpStatus(HttpStatus status) {
        return null;
    }

    @Override
    public List<ErrorDTO> findAllByStatusAndEntityName(HttpStatus status, String entityName) throws ResourceNotFoundException {
        List<Error> errors = getRepository().findAllByStatusAndEntityName(status, entityName);
        if ( errors.isEmpty() ) {
            throw getErrorProvider().notFound(Error.class.getSimpleName());
        }
        return getMapper().toDtoList(errors);
    }

}
