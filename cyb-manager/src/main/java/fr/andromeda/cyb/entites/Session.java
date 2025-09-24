package fr.andromeda.cyb.entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;
    private Double totalPrice;
    private boolean useSubscription;
    private LocalDateTime date;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionProduct> purchases = new ArrayList<>();

    public void addProduct(Product product, int amount, int unitPrice) {
        SessionProduct sessionProduct = new SessionProduct();
        sessionProduct.setSession(this);
        sessionProduct.setProduct(product);
        sessionProduct.setAmount(amount);
        sessionProduct.setUnitPrice(unitPrice);
        this.purchases.add(sessionProduct);
    }

    public Long getId() {
        return id;
    }

    public Session setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Session setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Session setClient(Client client) {
        this.client = client;
        return this;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public Session setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }

    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public Session setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Session setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public boolean isUseSubscription() {
        return useSubscription;
    }

    public Session setUseSubscription(boolean useSubscription) {
        this.useSubscription = useSubscription;
        return this;
    }

    public List<SessionProduct> getPurchases() {
        return purchases;
    }

    public Session setPurchases(List<SessionProduct> purchases) {
        this.purchases = purchases;
        return this;
    }

    @Override
    public String toString() {
        return "SessionProduitEntity{" +
                "id=" + id +
                ", client=" + client +
                ", startedOn=" + startedOn +
                ", finishedOn=" + finishedOn +
                ", totalPrice=" + totalPrice +
                ", useSubscription=" + useSubscription +
                ", purchases=" + purchases +
                '}';
    }

}
