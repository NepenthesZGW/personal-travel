package com.zgw.personaltravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/account")
public class PasswordController {

    @RequestMapping("password")
    public String passwordPage(){
        return "account/password";
    }
}
