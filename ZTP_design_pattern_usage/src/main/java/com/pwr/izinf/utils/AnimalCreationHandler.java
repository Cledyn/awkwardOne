package com.pwr.izinf.utils;

/**
 * Created by Sandra on 2016-04-02.
 */
public abstract class AnimalCreationHandler implements Chainable{

    private AnimalCreationHandler succesor;

    public void setSuccesor(AnimalCreationHandler succesor) {
        this.succesor = succesor;
    }

    public AnimalCreationHandler getSuccesor() {
        return succesor;
    }

    protected String forward(Request request){
        if(this.succesor != null){
           return this.getSuccesor().handle(request);
        }
        else return "redirect:/user_list.html";
    }

    public abstract String handle(Request request);
    
}
