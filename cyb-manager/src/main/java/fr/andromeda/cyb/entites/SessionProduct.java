package fr.andromeda.cyb.entites;

import jakarta.persistence.*;

@Entity
public class SessionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "session_id", foreignKey = @ForeignKey(name = "FK_sessionproduct_session"), nullable = false)
    private Session session;
    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_sessionproduct_product"), nullable = false)
    private Product product;
    private int amount;
    private double unitPrice;

    public Long getId() {
        return id;
    }

    public SessionProduct setId(Long id) {
        this.id = id;
        return this;
    }

    public Session getSession() {
        return session;
    }

    public SessionProduct setSession(Session session) {
        this.session = session;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public SessionProduct setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public SessionProduct setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public SessionProduct setUnitPrice(double unitPrice) {
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
