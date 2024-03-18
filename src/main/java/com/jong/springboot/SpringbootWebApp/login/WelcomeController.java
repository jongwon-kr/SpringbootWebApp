package com.jong.springboot.SpringbootWebApp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
    // login
    // GET, POST
    // GET요청에서만 login
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        model.put("name", getLoggedinUsername());
        return "welcome";
    }

    private String getLoggedinUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
