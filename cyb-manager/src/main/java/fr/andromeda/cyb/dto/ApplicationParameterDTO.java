package fr.andromeda.cyb.dto;

import fr.andromeda.api.dto.IDTO;
import fr.andromeda.cyb.enums.AppParameter;

public class ApplicationParameterDTO implements IDTO {

    private Long id;
    private AppParameter key;
    private String value;

    public Long getId() {
        return id;
    }

    public ApplicationParameterDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public AppParameter getKey() {
        return key;
    }

    public ApplicationParameterDTO setKey(AppParameter key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ApplicationParameterDTO setValue(String value) {
        this.value = value;
        return this;
    }

}
