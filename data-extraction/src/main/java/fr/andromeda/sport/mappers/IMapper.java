package fr.andromeda.sport.mappers;

import fr.andromeda.sport.dto.IDTO;
import fr.andromeda.sport.entities.IEntity;

import java.util.List;

public interface IMapper<U extends IDTO, V extends IEntity> {

    U toDto(V entity);
    V toEntity(U dto);
    List<U> toDtoList(List<V> entites);

}
