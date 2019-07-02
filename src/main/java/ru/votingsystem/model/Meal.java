package ru.votingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.restaurant.id = :restaurantId ORDER BY m.name DESC"),
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m WHERE m.id = :id AND m.restaurant.id = :restaurantId")
})

@Entity
@Table(name = "meals")
public class Meal extends AbstractNamedEntity {
    public static final String DELETE = "Meal.Delete";
    public static final String ALL_SORTED = "Meal.GetAllSorted";
    public static final String GET = "Meal.Get";

    @Column(name = "price")
    @NotNull
    private Integer price;

    @Column(name = "date_lunch")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(Integer id, String name, Integer price, LocalDateTime dateTime) {
        super(id, name);
        this.price = price;
        this.dateTime = dateTime;
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
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
