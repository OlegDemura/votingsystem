package ru.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.votingsystem.AuthorizedUser;
import ru.votingsystem.model.User;
import ru.votingsystem.repository.CrudUserRepository;
import ru.votingsystem.to.UserTo;
import ru.votingsystem.util.UserUtil;
import ru.votingsystem.util.exception.NotFoundException;

import java.util.List;

import static ru.votingsystem.util.UserUtil.prepareToSave;
import static ru.votingsystem.util.ValidationUtil.checkNotFound;
import static ru.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(CrudUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User get(int id) throws NotFoundException {
        return repository.findById(id).orElseThrow(()-> new NotFoundException("Not found user with id " + id));
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id)!=0, id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) throws NotFoundException {
        Assert.notNull(user, "user must not be null");
        repository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        repository.save(prepareToSave(UserUtil.updateFromTo(user, userTo), passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}

