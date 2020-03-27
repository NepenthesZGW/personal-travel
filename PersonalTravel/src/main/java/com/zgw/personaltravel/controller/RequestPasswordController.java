package com.zgw.personaltravel.controller;


import com.zgw.personaltravel.entity.Person;
import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/account/password")
public class RequestPasswordController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/init")
    public Object initPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Person person = (Person) request.getSession().getAttribute("personTemp");
        String pw=request.getParameter("password");
        String repw=request.getParameter("rePassword");
        if (!pw.equals(repw)){
            return new ResultBody<>().setCode(500).setMsg("密码不一致");
        }
        person.setPassword(pw);
        return accountService.change(person);
    }

    @RequestMapping("/change")
    public Object changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Person person = (Person) request.getSession().getAttribute("person");
        String oldPw=request.getParameter("oldPassword");
        String pw=request.getParameter("password");
        String repw=request.getParameter("rePassword");
        if (!pw.equals(repw)){
            return new ResultBody<>().setCode(500).setMsg("密码不一致");
        }
        person.setPassword(pw);
        return accountService.changeByPassword(person,oldPw);
    }
}
