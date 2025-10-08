package fr.andromeda.api.dto;

import java.time.LocalDateTime;

public interface AuditableDTO {

    LocalDateTime getCreatedOn();
    LocalDateTime getUpdatedOn();

}
