package fr.andromeda.cyb.mappers;

import fr.andromeda.cyb.dto.IDTO;
import fr.andromeda.cyb.entites.IEntity;

import java.util.List;

public interface IMapper<U extends IDTO, V extends IEntity> {

    U toDto(V entity);
    V toEntity(U dto);
    List<U> toDtoList(List<V> entites);

}
