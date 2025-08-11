package fr.andromeda.cyb.entites;

import fr.andromeda.cyb.dto.ClientDTO;
import fr.andromeda.cyb.entites.converters.DurationConverter;
import jakarta.persistence.*;

import java.time.Duration;

@Entity
public class ActiveSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = DurationConverter.class)
    private Duration remaining;
    @OneToOne
    private ClientEntity client;

}
