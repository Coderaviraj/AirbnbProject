package com.airbnb;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class A {
    public static void main(String[] args) {
//        PasswordEncoder en = new BCryptPasswordEncoder();
//        System.out.println(en.encode("testing"));

        String testing = BCrypt.hashpw("testing", BCrypt.gensalt(20));
        System.out.println(testing);
    }
}
