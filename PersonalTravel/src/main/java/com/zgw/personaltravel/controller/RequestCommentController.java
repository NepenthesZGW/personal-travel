package com.zgw.personaltravel.controller;

import com.zgw.personaltravel.entity.Comment;
import com.zgw.personaltravel.entity.Person;
import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RestController
@RequestMapping("/api/v1/comment")
public class RequestCommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("/edit")
    public ResultBody edit(HttpServletRequest request){

        Person person = (Person)request.getSession().getAttribute("person");
        Comment comment=new Comment();
        comment.setCreateid(person.getId());
        comment.setContent(request.getParameter("content").trim());
        comment.setLogid(Integer.parseInt(request.getParameter("logId")));
        comment.setHeadportrait(person.getHeadportrait());
        comment.setNickname(person.getNickname());
        comment.setCommentdate(new Date());
        if (request.getParameter("targetname")!=null&&!request.getParameter("targetname").equals("")){
            comment.setTargetnickname(request.getParameter("targetname"));
        }
        if (request.getParameter("targetid")!=null&&!request.getParameter("targetid").equals("")){
            comment.setTargetid(Integer.parseInt(request.getParameter("targetid")));
        }

        return commentService.create(comment);
    }


    @RequestMapping("/load")
    public ResultBody load(HttpServletRequest request){

        String logId = request.getParameter("logId");
        return commentService.findByLogId(logId);
    }
}
