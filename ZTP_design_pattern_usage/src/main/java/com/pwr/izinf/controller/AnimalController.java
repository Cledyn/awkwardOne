package com.pwr.izinf.controller;

import com.pwr.izinf.dao.AnimalDAO;
import com.pwr.izinf.dao.UserDAO;
import com.pwr.izinf.domain.Animal;
import com.pwr.izinf.domain.User;
import com.pwr.izinf.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AnimalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalController.class);
    private final AnimalDAO animalDAO;
    private final UserDAO userDAO;

    private static final AnimalCreationHandler allow = new AllowAnimalCreationHandler();
    private static final AnimalCreationHandler warn = new WarnBeforeAnimalCreationHandler();
    private static final AnimalCreationHandler forbid = new ForbidAnimalCreationHandler();
    private static final AnimalCreationHandler end = new EndAnimalCreationHandler();


    @Inject
    public AnimalController(final AnimalDAO animalDAO, final UserDAO userDAO) {
        this.animalDAO = animalDAO;
        this.userDAO = userDAO;
    }

    @RequestMapping("/animal_list_warn.html")
    public String getWarnView() {
        return "animal_list_warn";
    }

    @RequestMapping("/animal_list_forbid.html")
    public String getForbidView() {
        return "animal_list_forbid";
    }

    @RequestMapping("/unspecified_err.html")
    public String getUnspecifiedErrView() {
        return "unspecified_err";
    }

    @RequestMapping("/animal_list.html")
    public ModelAndView getListAnimalsView() {
        LOGGER.debug("Received request to get animal list view");
        ModelMap model = new ModelMap();
        model.addAttribute("animals", animalDAO.getList());
        return new ModelAndView("animal_list", model);
    }

    @RequestMapping(value = "/animal_delete.html")
    public ModelAndView deleteAnimal(@RequestParam("id") String animalId) {
        LOGGER.debug("Delete animal with id: {}", animalId);
        animalDAO.delete(animalId);
        ModelMap model = new ModelMap();
        model.addAttribute("animals", animalDAO.getList());
        return new ModelAndView("animal_list", model);
    }


    @RequestMapping(value = "/animal_create.html", method = RequestMethod.POST)
    public String createAnimal(@ModelAttribute("form") @Valid Animal form, BindingResult result, HttpServletRequest request) {
        LOGGER.debug("Received request to create {}, with result={}", form, result);
        if (result.hasErrors()) {
            return "animal_create";
        }
        String redirection;
        try {
            String ownerId = request.getParameter("selectOwner");
            User owner = userDAO.read(ownerId);
            redirection = applyChainOfResponsibility(form, owner);
        } catch (Exception e) {
            LOGGER.debug("Tried to create animal with existing id", e);
            result.reject("animal.error.exists");
            return "animal_create";
        }
        return redirection;
    }


    @RequestMapping(value = "/animal_create.html", method = RequestMethod.GET)
    public ModelAndView getCreateAnimalView(@ModelAttribute("form") Animal ani, BindingResult result) {
        ModelMap model = new ModelMap();
        model.addAttribute("form", new Animal());
        model.addAttribute("possibleOwners", userDAO.getList());
        return new ModelAndView("animal_create", model);
    }

    @RequestMapping("/animal_update.html")
    public ModelAndView getExistingAnimal(@ModelAttribute("form") Animal ani, BindingResult result, @RequestParam("id") String animalId) {
        Animal animal = animalDAO.read(animalId);
        ModelMap model = new ModelMap();
        model.addAttribute("animalToUpdate", animal);
        model.addAttribute("possibleOwners", userDAO.getList());
        return new ModelAndView("animal_update", model);

    }

    @RequestMapping(value = "/animal_edit.html", method = RequestMethod.POST)
    public String editAnimal(@ModelAttribute("form") Animal form,
                             BindingResult result, HttpServletRequest request) {
        String ownerId = request.getParameter("selectOwner");
        User animalOwner = userDAO.read(ownerId);
        form.setOwner(animalOwner);
        ModelMap model = new ModelMap();
        model.put("employee", animalDAO.update(form));
        return "redirect:/animal_list.html";
    }


    @RequestMapping(value = "/animal_show.html")
    public ModelAndView showAnimal(@RequestParam("id") String animalId) {
        ModelMap model = new ModelMap();
        Animal usr = animalDAO.read(animalId);
        model.addAttribute("chosenAnimal", usr);
        return new ModelAndView("animal_show", model);
    }

    @RequestMapping(value = "/animal", method = RequestMethod.POST)
    public Animal createAnimal(@RequestBody @Valid final Animal animal) {
        return animalDAO.save(animal);
    }

    @RequestMapping(value = "/animal", method = RequestMethod.GET)
    public List<Animal> listAnimals() {
        return animalDAO.getList();
    }

    private String applyChainOfResponsibility(Animal formData, User owner) {
        LOGGER.info("Invoking - design pattern: Chain of Responsibility");
        allow.setSuccesor(warn);
        warn.setSuccesor(forbid);
        forbid.setSuccesor(end);

        return allow.handle(new Request(animalDAO, formData, owner));
    }


}

