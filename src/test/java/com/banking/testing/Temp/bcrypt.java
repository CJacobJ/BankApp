package com.banking.testing.Temp;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class bcrypt {

    @Test
    public void bcryptCheckPwTest() {
        String plaintextPassword = "password";
        String hashedPassword = "$2a$12$keVuyE9WhELYKOEGWztt9eTldHWcJ1okYOV0M7dS11uJSTlsRFdKu";

        assertTrue("Password Incorrect", BCrypt.checkpw(plaintextPassword, hashedPassword));
    }
}
