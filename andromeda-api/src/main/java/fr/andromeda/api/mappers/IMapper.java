package fr.andromeda.api.mappers;

import fr.andromeda.api.dto.IDTO;
import fr.andromeda.api.entities.IEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface IMapper<D extends IDTO, E extends IEntity> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtoList(List<E> entites);

    List<E> toEntityList(List<D> dtos);

    //@Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E patchFromDto(D dto, @MappingTarget E entity);

}
