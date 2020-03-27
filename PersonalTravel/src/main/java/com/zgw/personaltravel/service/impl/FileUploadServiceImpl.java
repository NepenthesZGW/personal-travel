package com.zgw.personaltravel.service.impl;

import com.zgw.personaltravel.config.SystemStringUtils;
import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
public class FileUploadServiceImpl implements FileUploadService {


    @Override
    public ResultBody uploadImg(MultipartFile[] multipartfiles, String dir,String pathPostFix) {
        //MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        //List<MultipartFile> iter = multiRequest.getFiles("file");
        String imgPath="";
        boolean flag=true;
        try{
            if (null != multipartfiles && multipartfiles.length > 0) {
                for (MultipartFile picFile : multipartfiles) {
                    //MultipartFile picFile = multiRequest.getFile(iter.next());
                    if (null != picFile && null != picFile.getOriginalFilename()
                            && !"".equals(picFile.getOriginalFilename().trim())
                            && !"null".equals(picFile.getOriginalFilename().trim())) {

                        synchronized (FileUploadServiceImpl.this) {
                            String filePostFix = SystemStringUtils.getFilePostFix(picFile.getOriginalFilename());
                            String uuid = SystemStringUtils.getUUID();
                            String filename = dir+ pathPostFix+File.separator +uuid+filePostFix;
                            imgPath=imgPath+"|"+pathPostFix+File.separator +uuid+filePostFix;;
                            File file = new File(filename);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            picFile.transferTo(file);//上传至服务器

                        }
                    }
                }
            }

        } catch (IllegalStateException e) {
            flag=false;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            flag=false;
        }
            return flag==true?new ResultBody<String>().setMsg("ok").setCode(200).setBody(imgPath):
                    new ResultBody().setCode(500).setMsg("failed");
    }

    @Override
    public synchronized ResultBody<String> uploadText(byte[] bytes,String path, String filename) {
        boolean flag=true;

        File file=new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        FileOutputStream outputStream=null;
        try {
            outputStream=new FileOutputStream(path+ File.separator+filename);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            flag=false;
        } catch (IOException e) {
            e.printStackTrace();
            flag=false;
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        return  flag==true?new ResultBody<String>().setMsg("ok").setCode(200):
                new ResultBody().setCode(500).setMsg("failed");
    }
}
