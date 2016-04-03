package com.pwr.izinf.repository;

import com.pwr.izinf.domain.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;

public interface UserRepository extends CrudRepository<User, String> {

}
