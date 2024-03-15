package com.jong.springboot.SpringbootWebApp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    ///login => com.in28minutes.springboot.myfirstwebapp.login.LoginController => login.jsp

    // http://localhost:8080/login?name=jong
    @RequestMapping("login")
    public String goToLoginPage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        System.out.println("Request parm name = " + name);

        return "login";
    }
}
