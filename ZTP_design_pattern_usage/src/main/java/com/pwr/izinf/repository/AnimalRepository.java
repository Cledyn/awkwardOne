package com.pwr.izinf.repository;

import com.pwr.izinf.domain.Animal;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sandra on 2016-02-28.
 */
public interface AnimalRepository extends CrudRepository<Animal, String> {
}
