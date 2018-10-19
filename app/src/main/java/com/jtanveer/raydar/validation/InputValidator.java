package com.jtanveer.raydar.validation;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern EMAIL = Patterns.EMAIL_ADDRESS;
    private static final Pattern PASSWORD = Pattern.compile("^(?=.*[a-z0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    private static final Pattern MOBILE = Pattern.compile("^(\\+?6?01)[0-46-9]-*[0-9]{4,} *[0-9]{3,4}$");

    public boolean isValidEmail(String email) {
        return email != null && !TextUtils.isEmpty(email) && EMAIL.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        return password != null && !TextUtils.isEmpty(password) && PASSWORD.matcher(password).matches();
    }

    public boolean isValidMobile(String mobile) {
        return mobile != null && !TextUtils.isEmpty(mobile) && MOBILE.matcher(mobile).matches();
    }

}
