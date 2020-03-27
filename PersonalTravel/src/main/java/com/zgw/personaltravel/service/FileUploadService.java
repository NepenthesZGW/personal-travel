package com.zgw.personaltravel.service;

import com.zgw.personaltravel.entity.ResultBody;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    ResultBody<String> uploadImg(MultipartFile[] multipartfiles, String path,String pathPostFix);

    ResultBody<String> uploadText(byte[] bytes,String path, String filename);
}
