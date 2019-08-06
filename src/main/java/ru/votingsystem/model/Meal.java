package ru.votingsystem.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id = :id AND m.restaurant.id = :restaurantId"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.restaurant.id = :restaurantId ORDER BY m.description"),
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m WHERE m.id = :id AND m.restaurant.id = :restaurantId")
})

@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {
    public static final String DELETE = "Meal.Delete";
    public static final String ALL_SORTED = "Meal.GetAllSorted";
    public static final String GET = "Meal.Get";

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "price", nullable = false)
    @Range(min = 0, max = 10000)
    private Integer price;

    @Column(name = "date_lunch", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(String description, Integer price, LocalDateTime dateTime) {
        this(null, description, price, dateTime);
    }

    public Meal(Integer id, String description, Integer price, LocalDateTime dateTime) {
        super(id);
        this.description = description;
        this.price = price;
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "price=" + price +
                ", dateTime=" + dateTime +
                ", name='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
