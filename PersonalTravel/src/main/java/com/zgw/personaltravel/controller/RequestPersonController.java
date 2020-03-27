package com.zgw.personaltravel.controller;


import com.zgw.personaltravel.entity.Person;
import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/person")
public class RequestPersonController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/edit")
    public ResultBody edit(HttpServletRequest request
            , @RequestParam(value = "headportrait",required = false) MultipartFile multipartFile){
        Person person = (Person)request.getSession().getAttribute("person");
        person.setNickname(request.getParameter("nickname"));
        person.setGender(request.getParameter("gender").charAt(0));
        person.setIntroduction(request.getParameter("introduction"));
        ResultBody change = accountService.change(person);
        if (change.getCode()==200) {
            request.getSession().setAttribute("person",person);
            String filedir = "F://upload";
            if (null != multipartFile && null != multipartFile.getOriginalFilename()
                    && !"".equals(multipartFile.getOriginalFilename().trim())
                    && !"null".equals(multipartFile.getOriginalFilename().trim())) {

                synchronized (RequestPersonController.this) {
                    File file = new File(filedir+person.getHeadportrait());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    try {
                        multipartFile.transferTo(file);//上传至服务器
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return change;
    }
}
