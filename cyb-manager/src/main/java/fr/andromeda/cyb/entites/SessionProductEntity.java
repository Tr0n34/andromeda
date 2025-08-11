package fr.andromeda.cyb.entites;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

@Entity
public class SessionProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "session_id", foreignKey = @ForeignKey(name = "FK_sessionproduct_session"), nullable = false)
    private SessionEntity session;
    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_sessionproduct_product"), nullable = false)
    private ProductEntity product;
    private int amount;
    private double unitPrice;

    public Long getId() {
        return id;
    }

    public SessionProductEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public SessionEntity getSession() {
        return session;
    }

    public SessionProductEntity setSession(SessionEntity session) {
        this.session = session;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public SessionProductEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public SessionProductEntity setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public SessionProductEntity setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    @Override
    public String toString() {
        return "SessionProductEntity{" +
                "id=" + id +
                ", session=" + session +
                ", product=" + product +
                ", amount=" + amount +
                ", unitPrice=" + unitPrice +
                '}';
    }

}
