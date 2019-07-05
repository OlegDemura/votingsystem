package ru.votingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id = :id"),
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT v FROM Vote v WHERE v.restaurant.id = :restaurantId ORDER BY v.dateVoting DESC"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v WHERE v.id = :id AND v.restaurant.id = :restaurantId")
})

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    public static final String DELETE = "Vote.delete";
    public static final String ALL_SORTED = "Vote.getAllSorted";
    public static final String GET = "Vote.get";

    @Column(name = "date_voting")
    @NotNull
    private LocalDateTime dateVoting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Vote() {
    }

    public Vote(Integer id, LocalDateTime dateVoting) {
        super(id);
        this.dateVoting = dateVoting;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote{" +
                ", dateVoting=" + dateVoting +
                ", id=" + id +
                '}';
    }
}
