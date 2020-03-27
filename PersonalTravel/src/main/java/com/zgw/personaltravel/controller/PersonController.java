package com.zgw.personaltravel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class PersonController {

    @RequestMapping("/PersonalDynamic")
    public String PersonalDynamic(){
        return "account/personalDynamic";
    }


    @RequestMapping("/personMessage")
    public String personMessage(){
        return "account/personMessage";
    }

    @RequestMapping("/password")
    public String password(){
        return "account/passwordChange";
    }

}
