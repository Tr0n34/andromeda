package fr.andromeda.cyb.mappers.interfaces;

import fr.andromeda.cyb.dto.interfaces.IDTO;
import fr.andromeda.cyb.entites.interfaces.IEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface IMapper<D extends IDTO, E extends IEntity> {

    D toDto(E entity);

    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    E toEntity(D dto);

    List<D> toDtoList(List<E> entites);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchFromDto(D dto, @MappingTarget E entity);

}
