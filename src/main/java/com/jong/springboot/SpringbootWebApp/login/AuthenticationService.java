package com.jong.springboot.SpringbootWebApp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password) {

        boolean isValidUserName = username.equalsIgnoreCase("jongwon");
        boolean isValidPassword = password.equalsIgnoreCase("1234");

        return isValidPassword && isValidUserName;
    }
}
