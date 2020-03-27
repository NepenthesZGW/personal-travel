package com.zgw.personaltravel.controller;


import com.zgw.personaltravel.entity.Person;
import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/account/login")
public class RequestLoginController {


    @Autowired
    AccountService accountService;

    @RequestMapping("/valid")
    public ResultBody valid(HttpServletRequest request){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Person person = accountService.validLogin(account, password);
        if (person==null){
            return new ResultBody().setCode(500).setMsg("用户名或密码错误");
        }
        request.getSession().setAttribute("person",person);
        return new ResultBody().setCode(200).setMsg("登录成功");
    }

    @RequestMapping("/cancellation")
    public ResultBody cancellation(HttpServletRequest request){
        request.getSession().removeAttribute("person");
        request.getSession().removeAttribute("travelDetail");
        return ResultBody.success;
    }
}
