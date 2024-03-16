package com.jong.springboot.SpringbootWebApp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    private AuthenticationService authenticationService;

    @Autowired
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // login
    // GET, POST
    // GET요청에서만 login
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    // login?name=jongwon RequestParm
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {

        // Authentication 인증
        if (authenticationService.authenticate(name, password)) {
            model.put("name", name);
            // name - jongwon
            // password - 1234
            return "welcome";
        }

        model.put("errorMessage", "Invalid Credentials! Please Try again.");
        return "login";
    }
}
