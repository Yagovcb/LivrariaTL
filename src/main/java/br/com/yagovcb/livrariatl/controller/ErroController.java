package br.com.yagovcb.livrariatl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ErroController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = "/error")
    public String myerror() {
        return "<center><h1>Something went wrong</h1></center>";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
