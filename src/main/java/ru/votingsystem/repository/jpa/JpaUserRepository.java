package ru.votingsystem.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingsystem.model.User;
import ru.votingsystem.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()){
            em.persist(user);
            return user;
        }else {
            return em.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate()!=0;
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        List<User> list = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(list);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class)
                .getResultList();
    }
}