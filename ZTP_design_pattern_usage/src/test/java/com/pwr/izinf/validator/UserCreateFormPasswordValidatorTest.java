package com.pwr.izinf.validator;

import com.pwr.izinf.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserCreateFormPasswordValidatorTest {

    @Mock
    private Errors errors;

    private UserCreateFormPasswordValidator passwordValidator = new UserCreateFormPasswordValidator();

    @Test
    public void shouldSayItSupports_GivenItReceivesUserCreateFormClass() throws Exception {
        assertTrue(passwordValidator.supports(User.class));
    }

    @Test
    public void shouldSayItSupports_GivenItReceivesDifferentClass_ThenShouldReturnFalse() throws Exception {
        assertFalse(passwordValidator.supports(Object.class));
    }

    @Test
    public void shouldValidate_GivenPasswordsMatch_ThenErrorIsNotSet() throws Exception {
        User form = new User();
        form.setPassword("password");
        passwordValidator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }


}
