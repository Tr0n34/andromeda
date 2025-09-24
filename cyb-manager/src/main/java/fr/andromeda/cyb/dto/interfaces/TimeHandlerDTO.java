package fr.andromeda.cyb.dto.interfaces;

import java.time.LocalDateTime;

public interface TimeHandlerDTO<U extends IDTO> {

    LocalDateTime getCreatedOn();
    LocalDateTime getUpdatedOn();
    U setCreatedOn(LocalDateTime createdOn);
    U setUpdatedOn(LocalDateTime updatedOn);

}
