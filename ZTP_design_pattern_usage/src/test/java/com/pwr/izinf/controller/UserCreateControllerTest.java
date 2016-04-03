package com.pwr.izinf.controller;

import com.pwr.izinf.dao.AnimalDAO;
import com.pwr.izinf.dao.UserDAO;
import com.pwr.izinf.validator.UserCreateFormPasswordValidator;
import com.pwr.izinf.domain.User;
import com.pwr.izinf.dao.exception.UserAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserCreateControllerTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private AnimalDAO animalDAO;
    @Mock
    private BindingResult result;
    @Mock
    private UserCreateFormPasswordValidator passwordValidator;

    private UserController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserController(userDAO, animalDAO);
    }

    @Test
    public void shouldGetCreateUserPage() throws Exception {
        ModelAndView view = userController.getCreateUserView();
        assertEquals("View name should be right", "user_create", view.getViewName());
        assertTrue("View should contain attribute with form object", view.getModel().containsKey("form"));
        assertTrue("The form object should be of proper type", view.getModel().get("form") instanceof User);
    }

    @Test
    public void shouldCreateUser_GivenThereAreNoErrors_ThenTheUserShouldBeSavedAndUserListViewDisplayed() {
        when(result.hasErrors()).thenReturn(false);
        User form = new User();
        form.setId("id");
        form.setPassword("password");
        String view = userController.createUser(form, result);
        assertEquals("There should be proper redirect", "redirect:/user_list.html", view);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userDAO, times(1)).save(captor.capture());
        assertEquals(form.getId(), captor.getValue().getId());
        assertEquals(form.getPassword(), captor.getValue().getPassword());
    }

    @Test
    public void shouldCreateUser_GivenThereAreFormErrors_ThenTheFormShouldBeDisplayed() {
        when(result.hasErrors()).thenReturn(true);
        String view = userController.createUser(new User(), result);
        verify(userDAO, never()).save(any(User.class));
        assertEquals("View name should be right", "user_create", view);
    }

    @Test
    public void shouldCreateUser_GivenThereAlreadyExistUserWithId_ThenTheFormShouldBeDisplayed() {
        when(result.hasErrors()).thenReturn(false);
        when(userDAO.save(any(User.class))).thenThrow(UserAlreadyExistsException.class);
        String view = userController.createUser(new User(), result);
        verify(result).reject("user.error.exists");
        assertEquals("View name should be right", "user_create", view);
    }

}
