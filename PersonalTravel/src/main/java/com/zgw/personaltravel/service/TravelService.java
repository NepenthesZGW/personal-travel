package com.zgw.personaltravel.service;

import com.github.pagehelper.PageInfo;
import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.entity.Travellog;

public interface TravelService {
    ResultBody<Travellog> createLog(Travellog travellog);


    PageInfo<Travellog> findByPage(Integer pageNum, Integer pageSize,String keyWord);

    ResultBody<Travellog> change(Travellog body);

    PageInfo<Travellog> findByPageAndPersonId(Integer pageNum, Integer pageSize,Integer personId);

    ResultBody deleteTravel(int tarvelId);
}
