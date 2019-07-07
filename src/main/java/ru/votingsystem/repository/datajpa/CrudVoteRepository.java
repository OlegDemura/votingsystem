package ru.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.votingsystem.model.Vote;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

}
