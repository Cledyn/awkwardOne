package com.pwr.izinf.dao;

import com.pwr.izinf.domain.Animal;
import com.pwr.izinf.repository.AnimalRepository;
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
public class AnimalDAOImpl implements AnimalDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalDAOImpl.class);
    private final AnimalRepository repository;

    @Inject
    public AnimalDAOImpl(final AnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Animal save(@NotNull @Valid final Animal user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public Animal read(String id) {
        LOGGER.debug("Reading user by id {}", id);
        return repository.findOne(id);
    }

    @Override
    @Transactional
    public Animal update(Animal user) {
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
    public List<Animal> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return (List<Animal>) repository.findAll();
    }


}
