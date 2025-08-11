package fr.andromeda.cyb.entites;

import jakarta.persistence.*;

@Entity
public class ClientEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    @Column(nullable = true)
    private String email;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private ActiveSubscription activeSubscription;

    public Long getId() {
        return id;
    }

    public ClientEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ClientEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public ActiveSubscription getActiveSubscription() {
        return activeSubscription;
    }

    public ClientEntity setActiveSubscription(ActiveSubscription activeSubscription) {
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
