package com.pwr.izinf.utils;

import com.pwr.izinf.domain.Animal;

/**
 * Created by Sandra on 2016-04-02.
 */
public class ForbidAnimalCreationHandler extends AnimalCreationHandler {


    @Override
    public String handle(Request request) {
        return "redirect:/animal_list_forbid.html";
    }

}
