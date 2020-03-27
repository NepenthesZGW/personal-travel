package com.zgw.personaltravel.service;

import com.zgw.personaltravel.entity.ResultBody;

public interface FileReadService {
    ResultBody<String> readTextFile(String resource);
}
