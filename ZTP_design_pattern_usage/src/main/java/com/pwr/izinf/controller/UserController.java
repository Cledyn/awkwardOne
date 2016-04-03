package com.pwr.izinf.controller;

import com.pwr.izinf.dao.AnimalDAO;
import com.pwr.izinf.dao.UserDAO;
import com.pwr.izinf.domain.Animal;
import com.pwr.izinf.validator.UserCreateFormPasswordValidator;
import com.pwr.izinf.domain.User;
import com.pwr.izinf.dao.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserDAO userDAO;
    private final AnimalDAO animalDAO;
 //   private final UserCreateFormPasswordValidator passwordValidator;

    @Inject
    public UserController(final UserDAO userDAO, AnimalDAO animalDAO) {
        this.userDAO = userDAO;
        this.animalDAO = animalDAO;
    }


    @RequestMapping("/user_list.html")
    public ModelAndView getListUsersView() {
        LOGGER.debug("Received request to get user list view");
        ModelMap model = new ModelMap();
        model.addAttribute("users", userDAO.getList());
        return new ModelAndView("user_list", model);
    }

    @RequestMapping(value = "/delete.html")
    public ModelAndView deleteUser(@RequestParam("id") String userId) {
        LOGGER.debug("Delete user with id: {}", userId);
        userDAO.delete(userId);
        ModelMap model = new ModelMap();
        model.addAttribute("users", userDAO.getList());
        return new ModelAndView("user_list", model);
    }


    @RequestMapping(value = "/edit.html", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("form") User form,
                           BindingResult result) {
        ModelMap model = new ModelMap();
        model.put("employee", userDAO.update(form));
        return "redirect:/user_list.html";
    }


    @RequestMapping("/update.html")
    public ModelAndView getExistingUser(@RequestParam("id") String userId) {
        User user = userDAO.read(userId);
        ModelMap model = new ModelMap();
        model.addAttribute("userToUpdate", user);
        return new ModelAndView("user_update", "form", user);
    }

    @RequestMapping(value = "/show.html")
    public ModelAndView showUser(@RequestParam("id") String userId) {
        ModelMap model = new ModelMap();
        User usr = userDAO.read(userId);
        model.addAttribute("chosenUser", usr);
        model.addAttribute("animals",showAnimals(usr));
        return new ModelAndView("user_show", model);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody @Valid final User user) {
        LOGGER.debug("Received request to create the {}", user);
        return userDAO.save(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listUsers() {
        LOGGER.debug("Received request to list all users");
        return userDAO.getList();
    }
    @RequestMapping(value = "/user_create.html", method = RequestMethod.GET)
    public ModelAndView getCreateUserView() {
        LOGGER.debug("Received request for user create view");
        return new ModelAndView("user_create", "form",new User());
    }

    @RequestMapping(value = "/user_create.html", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("form") @Valid User form, BindingResult result) {
        LOGGER.debug("Received request to create {}, with result={}", form, result);
        if (result.hasErrors()) {
            return "user_create";
        }
        try {
            userDAO.save(form);
        } catch (UserAlreadyExistsException e) {
            LOGGER.debug("Tried to create user with existing id", e);
            result.reject("user.error.exists");
            return "user_create";
        }
        return "redirect:/user_list.html";
    }

    private List<Animal> showAnimals(User owner){
        List<Animal> animals = animalDAO.getList();
        List<Animal> ofInterest = new ArrayList<Animal>();
        for(Animal animal:animals){
            if(animal.getOwner().equals(owner))
                ofInterest.add(animal);
        }
        return ofInterest;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }


}
