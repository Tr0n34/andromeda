package fr.andromeda.api.mappers;

import fr.andromeda.api.dto.IDTO;
import fr.andromeda.api.entities.IEntity;
import org.mapstruct.Mapping;

public interface IAuditableMapper<D extends IDTO, E extends IEntity> extends IMapper<D, E> {

    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    E toEntity(D dto);

}
