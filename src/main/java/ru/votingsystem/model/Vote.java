package ru.votingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id = :id")
        //TODO Create named queries for getAllSorted()
})

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    public static final String DELETE = "Vote.delete";
    public static final String ALL_SORTED = "Vote.getAllSorted";

    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    @Column(name = "restaurant_id")
    @NotNull
    private Integer restaurantId;

    @Column(name = "date_voting")
    @NotNull
    private LocalDateTime dateVoting;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Integer id, Integer userId, Integer restaurantId, LocalDateTime dateVoting) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.dateVoting = dateVoting;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getDateVoting() {
        return dateVoting;
    }

    public void setDateVoting(LocalDateTime dateVoting) {
        this.dateVoting = dateVoting;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", dateVoting=" + dateVoting +
                ", id=" + id +
                '}';
    }
}
