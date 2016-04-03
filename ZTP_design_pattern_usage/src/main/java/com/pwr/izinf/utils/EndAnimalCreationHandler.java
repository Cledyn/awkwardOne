package com.pwr.izinf.utils;

/**
 * Created by Sandra on 2016-04-02.
 */
public class EndAnimalCreationHandler extends AnimalCreationHandler {


    @Override
    public String handle(Request request) {
        return "redirect:/unspecified_err.html";
    }
}
