package fr.andromeda.cyb.entites;

import fr.andromeda.cyb.entites.converters.DurationConverter;
import fr.andromeda.cyb.enums.ProductType;
import jakarta.persistence.*;

import java.time.Duration;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    private int price;
    @Column(nullable = true)
    @Convert(converter = DurationConverter.class)
    private Duration maxDuration;

    public Long getId() {
        return id;
    }

    public ProductEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public ProductEntity setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public ProductType getType() {
        return type;
    }

    public ProductEntity setType(ProductType type) {
        this.type = type;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public ProductEntity setPrice(int price) {
        this.price = price;
        return this;
    }

    public Duration getMaxDuration() {
        return maxDuration;
    }

    public ProductEntity setMaxDuration(Duration maxDuration) {
        this.maxDuration = maxDuration;
        return this;
    }
}
