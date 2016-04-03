package com.pwr.izinf.controller;

import com.pwr.izinf.dao.AnimalDAO;
import com.pwr.izinf.validator.UserCreateFormPasswordValidator;
import com.pwr.izinf.domain.User;
import com.pwr.izinf.dao.UserDAO;
import com.pwr.izinf.util.UserUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private AnimalDAO animalDAO;

    @Mock
    private UserCreateFormPasswordValidator validator;

    private UserController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserController(userDAO, animalDAO);
    }

    @Test
    public void shouldGetUserListPage() {
        List<User> userList = stubServiceToReturnExistingUsers(5);
        ModelAndView view = userController.getListUsersView();
        // verify UserDAO was called once
        verify(userDAO, times(1)).getList();
        assertEquals("View name should be right", "user_list", view.getViewName());
        assertEquals("Model should contain attribute with the list of users coming from the dao",
                userList, view.getModel().get("users"));
    }

    private List<User> stubServiceToReturnExistingUsers(int howMany) {
        List<User> userList = UserUtil.createUserList(howMany);
        when(userDAO.getList()).thenReturn(userList);
        return userList;
    }

}
