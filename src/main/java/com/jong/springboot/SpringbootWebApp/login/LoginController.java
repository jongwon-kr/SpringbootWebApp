package com.jong.springboot.SpringbootWebApp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    ///login => com.in28minutes.springboot.myfirstwebapp.login.LoginController => login.jsp

    // http://localhost:8080/login?name=jong
    @RequestMapping("login")
    public String goToLoginPage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        logger.debug("(debug)Request Parm is {}",name);
        logger.info("(info)Request Parm is {}",name);
        logger.info("(warn)Request Parm is {}",name);
        System.out.println("Request Parm is " + name);
        return "login";
    }
}
