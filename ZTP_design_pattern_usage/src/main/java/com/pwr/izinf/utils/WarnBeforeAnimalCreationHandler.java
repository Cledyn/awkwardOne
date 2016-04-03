package com.pwr.izinf.utils;

import com.pwr.izinf.domain.Animal;

/**
 * Created by Sandra on 2016-04-02.
 */
public class WarnBeforeAnimalCreationHandler extends AnimalCreationHandler{

    @Override
    public String handle(Request request) {
        if(request.getCandidateOwner().getMaxAnimalNo()==request.getCandidateOwner().getActualAnimalNo()){
            System.out.println("Actual:"+request.getCandidateOwner().getActualAnimalNo()+1+" Max: "+request.getCandidateOwner().getMaxAnimalNo());
            Animal formData = request.getFormData();
            request.getAnimalDAO().save((new Animal.Builder(formData.getId(),formData.getNick(), formData.getSpieces(),formData.getAge()).withOwner(request.getCandidateOwner()).build()));
            return "redirect:/animal_list_warn.html";
        }
        else{
           return forward(request);
        }
    }
}
