package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.dto.interfaces.IDTO;
import fr.andromeda.cyb.entites.IEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Optional;

public interface IMapper<D extends IDTO, E extends IEntity> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtoList(List<E> entites);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchFromDto(D dto, @MappingTarget E entity);

}
