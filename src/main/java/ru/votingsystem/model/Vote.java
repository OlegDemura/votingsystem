package ru.votingsystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date_voting"},
        name = "vote_unique_restaurant_date_idx")})
public class Vote extends AbstractBaseEntity {

    @Column(name = "date_voting", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime dateVoting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Vote() {
    }

    public Vote(LocalDateTime dateVoting) {
        super(null);
        this.dateVoting = dateVoting;
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
