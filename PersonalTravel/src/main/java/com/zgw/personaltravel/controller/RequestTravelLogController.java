package com.zgw.personaltravel.controller;


import com.github.pagehelper.PageInfo;
import com.zgw.personaltravel.entity.*;
import com.zgw.personaltravel.service.FileReadService;
import com.zgw.personaltravel.service.FileUploadService;
import com.zgw.personaltravel.service.SensitiveWordService;
import com.zgw.personaltravel.service.TravelService;
import com.zgw.personaltravel.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/travel")
public class RequestTravelLogController {


    @Autowired
    FileReadService fileReadService;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    TravelService travelService;

    @Autowired
    SensitiveWordService sensitiveWordService;
    @RequestMapping("/create")
    public Object create(HttpServletRequest request, @RequestParam(value = "images",required = false) MultipartFile[] multipartfiles) throws ParseException, IOException {

        if(request.getParameter("overt").equals("y")) {
            SensitiveWord introduction = sensitiveWordService.analysis(request.getParameter("title")+":"+request.getParameter("introduction"));
            if (introduction.getConclusionType() == 2 || introduction.getConclusionType() == 3) {
                return new ResultBody<Data>().setCode(500).setMsg("有敏感字").setBody(introduction.getData().get(0));
            }
        }
        String filedir = "F://upload/";
        Integer id = ((Person) request.getSession().getAttribute("person")).getId();
        Travellog travellog = new Travellog();
        travellog.setCreateid(id);
        travellog.setNickname(((Person) request.getSession().getAttribute("person")).getNickname());
        travellog.setHeadportrait(((Person) request.getSession().getAttribute("person")).getHeadportrait());
        travellog.setTitle(request.getParameter("title"));
        travellog.setPlace(request.getParameter("place"));
        travellog.setLogdate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("logdate")));
        travellog.setOvert(request.getParameter("overt"));
        if (request.getParameter("overt").equals("y")){
            filedir=filedir+"overt";
        }else {
            filedir=filedir+"private";
        }
        travellog.setStar(0);
        ResultBody<Travellog> log = travelService.createLog(travellog);

        if (log.getCode() == 200) {

            String postFix="/project/"+id+"/"+log.getBody().getId();


            if (fileUploadService.uploadText(request.getParameter("introduction").getBytes()
                    , filedir+postFix ,"introduction.txt").getCode() == 200) {
                log.getBody().setIntroduction(postFix + "/introduction.txt");
            }
            ResultBody<String> result = fileUploadService.uploadImg(multipartfiles, filedir,postFix + "/images");
            if (result.getCode() == 200) {
                if (fileUploadService.uploadText(result.getBody().getBytes()
                        , filedir+postFix + "/images","img.txt").getCode() == 200) {
                    log.getBody().setResource(postFix + "/images/img.txt");
                }
            }
            travelService.change(log.getBody());
        }
        /** 构建图片保存的目录 **/
       //truncate table 你的表名
        return log;
    }

    @RequestMapping("/publicData")
    public ResultBody<TravelDynamic> publicData(@RequestParam(defaultValue = "1") Integer pageNum
            ,@RequestParam(defaultValue = "5") Integer pageSize,HttpServletRequest request) {

        PageInfo<Travellog> travellogPage=	travelService.findByPage(pageNum,pageSize,request.getParameter("keyWord"));

        return new ResultBody<List<TravelDynamic>>().setCode(200).setBody(parseTravelDynamic(travellogPage.getList()));
    }

    @RequestMapping("/personalData")
    public ResultBody<TravelDynamic> personalData(@RequestParam(defaultValue = "1") Integer pageNum
            ,@RequestParam(defaultValue = "5") Integer pageSize,HttpServletRequest request) {
        String personId = request.getParameter("personId");
        Person person = (Person)request.getSession().getAttribute("person");
        if (person.getId().toString().equals(personId)) {
            PageInfo<Travellog> travellogPage = travelService.findByPageAndPersonId(pageNum, pageSize, person.getId());

            return new ResultBody<List<TravelDynamic>>().setCode(200).setBody(parseTravelDynamic(travellogPage.getList()));
        }else{
            return ResultBody.error;
        }
    }
    private List<TravelDynamic> parseTravelDynamic(List<Travellog>  list){

        List<TravelDynamic> travelDynamics=new ArrayList<>();


        for (Travellog item:list) {

            String strFix="";
            if (item.getOvert().equals("y")){
                strFix="/overt";
            }else {
                strFix="/private";
            }

            TravelDynamic travelDynamic=new TravelDynamic();
            travelDynamic.setTravellog(item);


            ResultBody<String> resource=fileReadService.readTextFile("F://upload"+strFix+item.getResource());


            if (resource.getCode()==200) {
                String[] split = resource.getBody().split("\\|");
                List<String> strings=new ArrayList<>();
                int imageCount=0;
                for (String str : split) {
                    if (!str.equals("")) {
                        imageCount++;
                        strings.add(strFix+str);
                    }
                }
                travelDynamic.setImages(strings);
                travelDynamic.setImageCount(imageCount);

            }
            ResultBody<String> introduction=fileReadService.readTextFile("F://upload"+strFix+item.getIntroduction());
            if (introduction.getCode()==200){
                travelDynamic.setContent(introduction.getBody());
            }
            travelDynamics.add(travelDynamic);
        }
        return  travelDynamics;
    }

    @RequestMapping("/detail")
    public ResultBody detail(@RequestBody TravelDynamic travelDynamic, HttpServletRequest request){

        System.out.println(travelDynamic);
        Person person = (Person)request.getSession().getAttribute("person");
        if (travelDynamic.getTravellog().getOvert().equals("n")){
            if (person==null||person.getId()!=travelDynamic.getTravellog().getCreateid()){
                return ResultBody.error;
            }
        }else {
            request.getSession().setAttribute("travelDetail", travelDynamic);
        }
        return new ResultBody().setCode(200);
    }
    @RequestMapping("/edit")
    public ResultBody edit(@RequestBody TravelDynamic travelDynamic, HttpServletRequest request){

        System.out.println(travelDynamic);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat.format(travelDynamic.getTravellog().getLogdate());
//
        Person person = (Person)request.getSession().getAttribute("person");
        if (travelDynamic.getTravellog().getCreateid()==person.getId()) {
            request.getSession().setAttribute("travelDetail", travelDynamic);
            return ResultBody.success;
        }else {
            return ResultBody.error;
        }
    }
    @RequestMapping("/deleteTravel")
    public ResultBody deleteTravel( HttpServletRequest request){

        System.out.println(request.getParameter("travelId"));
        String createId = request.getParameter("createId");
        Person person = (Person)request.getSession().getAttribute("person");
        if (person.getId().toString().equals(createId)) {
//        return   travelService.deleteTravel(Integer.parseInt(request.getParameter("tarvelId")));
            return ResultBody.success;
        }else {
            return ResultBody.error;
        }
    }

    @RequestMapping("/editSubmit")
    public ResultBody editSubmit( HttpServletRequest request, @RequestParam(value = "images",required = false) MultipartFile[] multipartfiles,
                                  @RequestParam(value = "deleteImage",required = false) String[] deleteImages) throws ParseException {

        if(request.getParameter("overt").equals("y")) {
            SensitiveWord introduction = sensitiveWordService.analysis(request.getParameter("title")+":"+request.getParameter("introduction"));
            if (introduction.getConclusionType() == 2 || introduction.getConclusionType() == 3) {
                return new ResultBody<Data>().setCode(500).setMsg("有敏感字").setBody(introduction.getData().get(0));
            }
        }
        Travellog travellog = ((TravelDynamic)request.getSession().getAttribute("travelDetail")).getTravellog();
        Person person = (Person)request.getSession().getAttribute("person");
        if (travellog.getCreateid()!=person.getId()){
            return ResultBody.error;
        }
        String overtTemp=travellog.getOvert();
        String oldStr="";
        if (overtTemp.equals("y")){
             oldStr="overt";
        }else {
            oldStr="private";
        }


        String filedir = "F://upload/";
        Integer id = travellog.getCreateid();

        travellog.setTitle(request.getParameter("title"));
        travellog.setPlace(request.getParameter("place"));
        travellog.setLogdate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("logdate")));

        travellog.setOvert(request.getParameter("overt"));
        travellog.setStar(0);
        ResultBody<Travellog> resultBody = travelService.change(travellog);


        if (resultBody.getCode() == 200) {

            String newStr = "";
            if (request.getParameter("overt").equals("y")) {
                newStr = "overt";
            } else {
                newStr = "private";
            }
            filedir = filedir + newStr;
            String postFix="/project/"+id+"/"+travellog.getId();

            if (!overtTemp.equals(request.getParameter("overt"))){

                FileUtils.copyFolder("F://upload/"+oldStr+postFix,"F://upload/" + newStr + postFix);
                FileUtils.deleteDir(new File("F://upload/"+oldStr+postFix));

            }

            fileUploadService.uploadText(request.getParameter("introduction").getBytes()
                        , filedir+postFix, "introduction.txt");


            ResultBody<String> oldPaths = fileReadService.readTextFile(filedir+travellog.getResource());
           if (oldPaths.getCode()==200) {
               ResultBody<String> result = fileUploadService.uploadImg(multipartfiles, filedir, postFix + "/images");
               if (result.getCode() == 200) {
                   String s = imagePath(oldPaths.getBody(), deleteImages,filedir);
                   fileUploadService.uploadText((s+result.getBody()).getBytes(), filedir+postFix + "/images", "img.txt");
               }
           }
        }
        return resultBody;
    }
    private String imagePath(String oldPath,String[] deletePath,String filedir){

        String result="";
            String[] split = oldPath.split("\\|");
        if (deletePath!=null) {
            for (String old : split) {
                for (String item : deletePath) {
                    if (old != null && !old.equals("") && !item.endsWith(old)) {
                        result = result + "|" + old;
                    } else {
                        if (old != null && !old.equals("")) {

                            File file = new File(filedir + old);
                            if (file.exists())
                                file.delete();
                        }
                    }

                }
            }
        }
        else {
            return oldPath;
        }
            return result;
    }


}
