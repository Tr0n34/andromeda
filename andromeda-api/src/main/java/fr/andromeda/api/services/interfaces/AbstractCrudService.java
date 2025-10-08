package fr.andromeda.api.services.interfaces;

import fr.andromeda.api.dto.IDTO;
import fr.andromeda.api.entities.IEntity;
import fr.andromeda.api.errors.ErrorProvider;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.mappers.IMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractCrudService <D extends IDTO, E extends IEntity, R extends JpaRepository<E, ID>, ID> implements ICrudService<D, E,R, ID> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCrudService.class);

    protected final IMapper<D, E> mapper;
    protected final R repository;
    private final String entityName;
    private final ErrorProvider errorProvider;

    public AbstractCrudService(IMapper<D, E> mapper, R repository, String entityName) {
        this.entityName = entityName;
        this.repository = repository;
        this.mapper = mapper;
        this.errorProvider = null;
    }

    protected AbstractCrudService(IMapper<D, E> mapper, R repository, String entityName, ErrorProvider errorProvider) {
        this.mapper = mapper;
        this.repository = repository;
        this.entityName = entityName;
        this.errorProvider = errorProvider;
    }

    public D get(ID id) throws ResourceNotFoundException {
        E entity = repository.findById(id).orElseThrow(() -> errorProvider.notFound(entityName));
        logger.debug("get entity with id {}", id);
        return mapper.toDto(entity);
    }

    public D create(D dto) throws ResourceNotFoundException {
        E entity = repository.save(mapper.toEntity(dto));
        logger.debug("create entity with id {}", entity);
        return mapper.toDto(entity);
    }

    public D update(ID id, D dto) throws ResourceNotFoundException {
        E entity = loadEntity(id);
        E updated = mapper.toEntity(dto);
        logger.debug("update entity with id {}", id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(ID id) throws ResourceNotFoundException {
        try {
            repository.deleteById(id);
            logger.debug("delete entity with id {}", id);
        } catch (EmptyResultDataAccessException e) {
            throw errorProvider.notFound(entityName);
        }
    }

    public void patch(ID id, D dto) throws ResourceNotFoundException {
        E entity = loadEntity(id);
        mapper.patchFromDto(dto, entity);
        mapper.toDto(repository.save(entity));
    }

    public List<D> findAll() {
        List<E> entities = repository.findAll();
        return mapper.toDtoList(entities);
    }

    public void createAll(List<D> dtos) {
        getRepository().saveAll(dtos.stream()
                .map(getMapper()::toEntity)
                .toList());
    }

    public String getEntityName() {
        return entityName;
    }

    public R getRepository() {
        return repository;
    }

    public IMapper<D, E> getMapper() {
        return mapper;
    }

    public ErrorProvider getErrorProvider() {
        return errorProvider;
    }

    public E loadEntity(ID id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> errorProvider.notFound(entityName));
    }

}
