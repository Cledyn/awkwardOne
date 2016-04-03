package com.pwr.izinf.controller;

import com.pwr.izinf.dao.AnimalDAO;
import com.pwr.izinf.domain.User;
import com.pwr.izinf.dao.UserDAO;
import com.pwr.izinf.util.UserUtil;
import com.pwr.izinf.validator.UserCreateFormPasswordValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    private static final int SAMPLE_MAX_ANIMAL_NUMBER = 4;
    @Mock
    private UserDAO userDAO;
    @Mock
    private AnimalDAO animalDAO;

    private UserController userRestController;
    private UserCreateFormPasswordValidator passwordValidator;

    @Before
    public void setUp() throws Exception {
        userRestController = new UserController(userDAO,animalDAO);
    }

    @Test
    public void shouldCreateUser() {
        final User savedUser = stubServiceToReturnStoredUser();
        User userToSave = new User.Builder("SampleId", "SamplePassword",SAMPLE_MAX_ANIMAL_NUMBER).build();
       // final User user = UserUtil.createUser();
        User returnedUser = userRestController.createUser(userToSave);
        // verify user was passed to UserDAO
        verify(userDAO, times(1)).save(userToSave);
        assertEquals("Returned user should come from the dao", savedUser, returnedUser);
    }

    private User stubServiceToReturnStoredUser() {
        final User user = UserUtil.createUser();
        when(userDAO.save(any(User.class))).thenReturn(user);
        return user;
    }

    @Test
    public void shouldListAllUsers() {
        stubServiceToReturnExistingUsers(10);
        Collection<User> users = userRestController.listUsers();
        assertNotNull(users);
        assertEquals(10, users.size());
        // verify user was passed to UserDAO
        verify(userDAO, times(1)).getList();
    }

    private void stubServiceToReturnExistingUsers(int howMany) {
        when(userDAO.getList()).thenReturn(UserUtil.createUserList(howMany));
    }

}
