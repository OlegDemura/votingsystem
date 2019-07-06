package ru.votingsystem.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.model.User;
import ru.votingsystem.model.Vote;
import ru.votingsystem.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.getId(), restaurantId) == null) {
            return null;
        }

        vote.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        vote.setUser(em.getReference(User.class, userId));
        vote.setRestaurant(em.getReference(Restaurant.class, restaurantId));

        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId, int restaurantId) {
        return em.createNamedQuery(Vote.DELETE, Vote.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }

    @Override
    public Vote get(int id, int restaurantId) {
        List<Vote> list = em.createNamedQuery(Vote.GET, Vote.class)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
        return DataAccessUtils.singleResult(list);
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        return em.createNamedQuery(Vote.ALL_SORTED, Vote.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }
}
