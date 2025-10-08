package fr.andromeda.cyb.entites;

import fr.andromeda.api.entities.AuditableEntity;
import fr.andromeda.api.entities.IEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client extends AuditableEntity  implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String lastName;
    private String firstName;
    @Column(nullable = true)
    private String email;
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private ActiveSubscription activeSubscription;

    public Long getId() {
        return id;
    }

    public Client setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public ActiveSubscription getActiveSubscription() {
        return activeSubscription;
    }

    public Client setActiveSubscription(ActiveSubscription activeSubscription) {
        this.activeSubscription = activeSubscription;
        return this;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", activeSubscription=" + activeSubscription +
                '}';
    }

}
