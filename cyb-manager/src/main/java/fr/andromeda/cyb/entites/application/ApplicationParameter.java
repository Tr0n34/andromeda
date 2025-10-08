package fr.andromeda.cyb.entites.application;

import fr.andromeda.api.entities.IEntity;
import fr.andromeda.cyb.enums.AppParameter;
import jakarta.persistence.*;

@Entity
@Table(name = "application_parameters")
public class ApplicationParameter implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AppParameter key;
    private String value;

    @Override
    public Long getId() {
        return id;
    }

    public ApplicationParameter setId(Long id) {
        this.id = id;
        return this;
    }

    public AppParameter getKey() {
        return key;
    }

    public ApplicationParameter setKey(AppParameter key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ApplicationParameter setValue(String value) {
        this.value = value;
        return this;
    }

}
