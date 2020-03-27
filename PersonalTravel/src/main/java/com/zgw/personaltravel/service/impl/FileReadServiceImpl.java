package com.zgw.personaltravel.service.impl;


import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.service.FileReadService;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileReadServiceImpl implements FileReadService {


    @Override
    public synchronized ResultBody<String> readTextFile(String resource) {
        boolean flag=true;

        File file = new File(resource);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            flag=false;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    flag = false;
                }
            }
        }
        return flag==true?new ResultBody<String>().setCode(200).setMsg("ok").setBody(sbf.toString()):
                new ResultBody<>().setCode(500).setMsg("failed");
    }
}
