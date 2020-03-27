package com.zgw.personaltravel.mapper;

import com.zgw.personaltravel.entity.Person;
import com.zgw.personaltravel.entity.PersonExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * PersonDAO继承基类
 */
@Repository
@Mapper
public interface PersonDAO extends MyBatisBaseDao<Person, Integer, PersonExample> {
    Person findByAccount(String account);
}