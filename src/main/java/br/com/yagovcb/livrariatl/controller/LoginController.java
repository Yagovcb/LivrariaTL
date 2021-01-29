package br.com.yagovcb.livrariatl.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping
    @RequestMapping("/login")
    @Secured({"ROLE_ADM","ROLE_USUARIO"})
    public String login(){
        return "login";
    }

}
