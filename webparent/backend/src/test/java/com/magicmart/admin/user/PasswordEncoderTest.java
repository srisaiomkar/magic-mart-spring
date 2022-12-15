package com.magicmart.admin.user;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void testPasswordEncode(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = "testpass123";
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        System.out.println(encodedPassword);
        Boolean matches = bCryptPasswordEncoder.matches(password, encodedPassword);
        assertTrue(matches);
    }
    
}
