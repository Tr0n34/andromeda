package fr.andromeda.cyb.entites;

import fr.andromeda.cyb.entites.converters.DurationConverter;
import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Table(name = "active_subscriptions")
public class ActiveSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = DurationConverter.class)
    private Duration remaining;
    @OneToOne
    private Client client;

    public Long getId() {
        return id;
    }

    public ActiveSubscription setId(Long id) {
        this.id = id;
        return this;
    }

    public Duration getRemaining() {
        return remaining;
    }

    public ActiveSubscription setRemaining(Duration remaining) {
        this.remaining = remaining;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public ActiveSubscription setClient(Client client) {
        this.client = client;
        return this;
    }

}
