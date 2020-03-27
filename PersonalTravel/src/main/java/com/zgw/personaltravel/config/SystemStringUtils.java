package com.zgw.personaltravel.config;


import org.springframework.http.converter.json.GsonBuilderUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class SystemStringUtils {


    public static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getFilePostFix(String filename){
        String[] split = filename.split("\\.");
        return "."+split[1];
    }
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static String parseDate(Date date){
        return sdf.format(date);
    }

    public static void main(String[] args) {
        String str="dsad|dass";
        String[] split = str.split("\\|");
        Arrays.stream(split).forEach(System.out::println);
    }
}
