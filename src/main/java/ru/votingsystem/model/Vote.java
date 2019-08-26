package ru.votingsystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_voting"},
        name = "vote_unique_user_date_idx")})
public class Vote extends AbstractBaseEntity {

    @Column(name = "date_voting", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate dateVoting;

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

    public Vote(LocalDate dateVoting, User user, Restaurant restaurant) {
        this(null, dateVoting, user, restaurant);
    }

    public Vote(Integer id, LocalDate dateVoting, User user, Restaurant restaurant) {
        super(id);
        this.dateVoting = dateVoting;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDate getDateVoting() {
        return dateVoting;
    }

    public void setDateVoting(LocalDate dateVoting) {
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
