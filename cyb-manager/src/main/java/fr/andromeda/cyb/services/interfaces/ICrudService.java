package fr.andromeda.cyb.services.interfaces;

import fr.andromeda.cyb.configurations.errors.ErrorProvider;
import fr.andromeda.cyb.dto.interfaces.IDTO;
import fr.andromeda.cyb.entites.interfaces.IEntity;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.mappers.interfaces.IMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICrudService<D extends IDTO, E extends IEntity, R extends JpaRepository<E, ID>, ID> {

    D get(ID id) throws ResourceNotFoundException;

    D create(D dto) throws ResourceNotFoundException;

    D update(ID id, D dto) throws ResourceNotFoundException;

    void delete(ID id) throws ResourceNotFoundException;

    void patch(ID id, D dto) throws ResourceNotFoundException;

    List<D> findAll();

    String getEntityName();

    R getRepository();

    IMapper<D, E> getMapper();

    ErrorProvider getErrorProvider();

    E loadEntity(ID id) throws ResourceNotFoundException;

}
