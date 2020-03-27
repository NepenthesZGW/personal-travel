package com.zgw.personaltravel.controller;


import com.zgw.personaltravel.entity.TravelDynamic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/travel")
public class TravelLogController {


    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, HttpServletResponse response){
        TravelDynamic<String> travelDetail =(TravelDynamic<String>)request.getSession().getAttribute("travelDetail");
        if (travelDetail!=null) {
            return "travel/detail";
        }else{
            return "errorPage";
        }
    }

    @RequestMapping("/create")
    public String create() {
        return "travel/createLog";
    }

    @RequestMapping("/dynamic")
    public String dynamic(HttpServletRequest request) {
        String keyWord = request.getParameter("keyWord");
        request.setAttribute("keyWord",keyWord);
        return "travel/dynamic";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "travel/editTravelLog";
    }


}