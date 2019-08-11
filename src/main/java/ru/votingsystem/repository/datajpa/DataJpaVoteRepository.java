package ru.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository {

    @Autowired
    private CrudVoteRepository repository;

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    private CrudUserRepository userRepository;

    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.getId(), restaurantId, LocalDate.now()) == null) {
            return null;
        }

        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        vote.setUser(userRepository.getOne(userId));
        return repository.save(vote);
    }

    public boolean delete(int id, int userId, int restaurantId) {
        return repository.delete(id, userId, restaurantId) != 0;
    }

    public Vote get(int id, int restaurantId, LocalDate localDate) {
        return repository.getOneVote(id, restaurantId, localDate);
    }

    public List<Vote> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public Integer countAllByDateVoting(int restaurantId) {
        return repository.countAllByDateVoting(restaurantId);
    }
}
