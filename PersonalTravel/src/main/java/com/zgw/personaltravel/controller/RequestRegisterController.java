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
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/account/register")
public class RequestRegisterController {


    @Autowired
    private AccountService accountService;

    @RequestMapping("/valid")
    public Object valid(HttpServletRequest request
            , HttpServletResponse response
            , @RequestParam(value = "headportrait",required = false) MultipartFile multipartFile) throws IOException {
        Person p=new Person();
        p.setAccount( request.getParameter("phone"));
        p.setNickname(request.getParameter("nickname"));
        p.setGender(request.getParameter("gender").charAt(0));
        p.setIntroduction(request.getParameter("introduction"));
        p.setPassword("0");
        ResultBody<Person> resultBody = accountService.insertPerson(p);

        if (resultBody.getCode()==200) {

            String dir="F://upload";
            String postFix = "/person/project/"+resultBody.getBody().getId();
            String filename="";
            if (null != multipartFile && null != multipartFile.getOriginalFilename()
                    && !"".equals(multipartFile.getOriginalFilename().trim())
                    && !"null".equals(multipartFile.getOriginalFilename().trim())) {

                postFix=postFix+"/images" + File.separator + "head.png";
                synchronized (RequestRegisterController.this) {
                    File file = new File(dir+postFix);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    multipartFile.transferTo(file);//上传至服务器
                }
            }
            else{
                postFix= "/person/project/img/head.png";
            }

            resultBody.getBody().setHeadportrait(postFix);
            accountService.change(resultBody.getBody());
            request.getSession().setAttribute("personTemp", resultBody.getBody());
        }
        return resultBody;
    }
}
