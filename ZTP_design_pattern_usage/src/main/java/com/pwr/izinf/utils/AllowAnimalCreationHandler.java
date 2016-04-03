package com.pwr.izinf.utils;

import com.pwr.izinf.domain.Animal;

/**
 * Created by Sandra on 2016-04-02.
 */
public class AllowAnimalCreationHandler extends AnimalCreationHandler {

    @Override
    public String handle(Request request) {
        if (request.getCandidateOwner().getMaxAnimalNo() > request.getCandidateOwner().getActualAnimalNo()) {
            Animal formData = request.getFormData();
            request.getAnimalDAO().save((new Animal.Builder(formData.getId(), formData.getNick(), formData.getSpieces(), formData.getAge()).withOwner(request.getCandidateOwner()).build()));
            return "redirect:/animal_list.html";
        } else {
            return forward(request);
        }

    }
}
