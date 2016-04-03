package com.pwr.izinf.utils;

import com.pwr.izinf.dao.AnimalDAO;
import com.pwr.izinf.domain.Animal;
import com.pwr.izinf.domain.User;

/**
 * Created by Sandra on 2016-04-02.
 */
public class Request {

    private AnimalDAO animalDAO;
    private Animal formData;
    private User candidateOwner;

    public Request(AnimalDAO animalDAO, Animal formData, User candidateOwner) {
        this.animalDAO = animalDAO;
        this.formData = formData;
        this.candidateOwner = candidateOwner;
    }

    public User getCandidateOwner() {
        return candidateOwner;
    }

    public void setCandidateOwner(User candidateOwner) {
        this.candidateOwner = candidateOwner;
    }

    public Animal getFormData() {
        return formData;
    }

    public void setFormData(Animal formData) {
        this.formData = formData;
    }

    public AnimalDAO getAnimalDAO() {
        return animalDAO;
    }

    public void setAnimalDAO(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }


}
