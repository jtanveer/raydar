package com.jtanveer.raydar;

import com.jtanveer.raydar.form.LoginForm;
import com.jtanveer.raydar.viewmodel.LoginViewModel;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void inputFields_CorrectInput_ReturnsTrue() {
        LoginViewModel vm = new LoginViewModel();
        vm.init();
        LoginForm form = vm.getLogin();
        form.getFields().setEmail("hello@world.com");
        form.getFields().setPassword("123456@#");
        assertTrue("Form is not valid", form.isValid(false));
    }

    @Test
    public void inputFields_EmptyInput_ReturnsFalse() {
        LoginViewModel vm = new LoginViewModel();
        vm.init();
        LoginForm form = vm.getLogin();
        form.getFields().setEmail("");
        form.getFields().setPassword("");
        assertFalse("Form is not valid", form.isEmpty());
    }

    @Test
    public void email_IncorrectInput_ReturnsFalse() {
        LoginViewModel vm = new LoginViewModel();
        vm.init();
        LoginForm form = vm.getLogin();
        form.getFields().setEmail("hello@world");
        assertFalse("Email is valid", form.isEmailValid(true));
        assertEquals("Wrong message displayed", "Email is not valid", form.getEmailError());
    }

    @Test
    public void password_IncorrectInput_ReturnsFalse() {
        LoginViewModel vm = new LoginViewModel();
        vm.init();
        LoginForm form = vm.getLogin();
        form.getFields().setPassword("123qwe");
        assertFalse("Password is valid", form.isPasswordValid(true));
        assertEquals("Wrong message displayed", "Password should contain one " +
                "special character and minimum 8 character required", form.getPasswordError());
        form.getFields().setPassword("123qwert");
        assertFalse("Password is valid", form.isPasswordValid(true));
        assertEquals("Wrong message displayed", "Password should contain one " +
                "special character and minimum 8 character required", form.getPasswordError());
    }
}