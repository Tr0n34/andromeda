package fr.andromeda.cyb.entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ClientEntity client;
    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;
    private Double totalPrice;
    private boolean useSubscription;
    private LocalDateTime date;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionProductEntity> purchases = new ArrayList<>();

    public void addProduct(ProductEntity product, int amount, int unitPrice) {
        SessionProductEntity sessionProduct = new SessionProductEntity();
        sessionProduct.setSession(this);
        sessionProduct.setProduct(product);
        sessionProduct.setAmount(amount);
        sessionProduct.setUnitPrice(unitPrice);
        this.purchases.add(sessionProduct);
    }

    public Long getId() {
        return id;
    }

    public SessionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public SessionEntity setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public ClientEntity getClient() {
        return client;
    }

    public SessionEntity setClient(ClientEntity client) {
        this.client = client;
        return this;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public SessionEntity setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }

    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public SessionEntity setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public SessionEntity setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public boolean isUseSubscription() {
        return useSubscription;
    }

    public SessionEntity setUseSubscription(boolean useSubscription) {
        this.useSubscription = useSubscription;
        return this;
    }

    public List<SessionProductEntity> getPurchases() {
        return purchases;
    }

    public SessionEntity setPurchases(List<SessionProductEntity> purchases) {
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
