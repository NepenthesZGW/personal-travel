package com.zgw.personaltravel.mapper;

import com.zgw.personaltravel.entity.Travellog;
import com.zgw.personaltravel.entity.TravellogExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TravellogDAO继承基类
 */
@Repository
@Mapper
public interface TravellogDAO extends MyBatisBaseDao<Travellog, Integer, TravellogExample> {

    List<Travellog> findByKeyWord(String keyWord,String overt);
}