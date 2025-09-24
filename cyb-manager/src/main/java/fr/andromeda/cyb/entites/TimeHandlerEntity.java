package fr.andromeda.cyb.entites;

import java.time.LocalDateTime;

public interface TimeHandlerEntity<U extends IEntity> {

    LocalDateTime getCreatedOn();
    LocalDateTime getUpdatedOn();
    U setCreatedOn(LocalDateTime createdOn);
    U setUpdatedOn(LocalDateTime updatedOn);

}
