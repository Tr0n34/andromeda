package fr.andromeda.cyb.dto.interfaces;

import java.time.LocalDateTime;

public interface AuditableDTO {

    LocalDateTime getCreatedOn();
    LocalDateTime getUpdatedOn();
    //<U extends IDTO>  U setCreatedOn(LocalDateTime createdOn);
    //<U extends IDTO> U setUpdatedOn(LocalDateTime updatedOn);

}
