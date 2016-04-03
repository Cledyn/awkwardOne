package com.pwr.izinf.dao;

import com.pwr.izinf.domain.Animal;

import java.util.List;

/**
 * Created by Sandra on 2016-02-28.
 */
public interface AnimalDAO {
    Animal save(Animal Animal);
    Animal read(String id);
    Animal update(Animal Animal);
    void delete(String id);
    List<Animal> getList();
}
