package com.pwr.izinf.dao;

import com.pwr.izinf.domain.User;
import com.pwr.izinf.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);
    private final UserRepository repository;

    @Inject
    public UserDAOImpl(final UserRepository repository) {
        this.repository = repository;
        //this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public User save(@NotNull @Valid final User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public User read(String id) {
        LOGGER.debug("Reading user by id {}", id);
        return repository.findOne(id);
    }

    @Override
    @Transactional
    public User update(User user) {
        return repository.save(user);
    }


    @Override
    @Transactional
    public void delete(String id) {
        LOGGER.debug("Deleting user with id {}", id);
        repository.delete(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return (List<User>) repository.findAll();
    }

}
