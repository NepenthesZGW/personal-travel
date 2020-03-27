package com.zgw.personaltravel.controller;


import com.zgw.personaltravel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class RegisterController {

    @RequestMapping("/register")
    public String registerPage(){
        return "account/register";
    }


}
