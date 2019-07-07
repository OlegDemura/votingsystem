package ru.votingsystem.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingsystem.model.Meal;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }

        meal.setRestaurant(em.getReference(Restaurant.class, restaurantId));

        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        List<Meal> list = em.createNamedQuery(Meal.GET, Meal.class)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
        return DataAccessUtils.singleResult(list);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }
}
