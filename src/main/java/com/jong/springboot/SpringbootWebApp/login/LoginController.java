package com.jong.springboot.SpringbootWebApp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    // login
    @RequestMapping("login")
    public String goToLoginPage() {
        return "login";
    }
}
