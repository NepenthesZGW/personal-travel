package com.zgw.personaltravel.service.impl;


import com.zgw.personaltravel.entity.Person;
import com.zgw.personaltravel.entity.PersonExample;
import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.entity.TravellogExample;
import com.zgw.personaltravel.mapper.PersonDAO;
import com.zgw.personaltravel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    PersonDAO personDAO;
    @Override
    public ResultBody<Person> insertPerson(Person person) {

        person.setIdentity('0');
        PersonExample example=new PersonExample();
        example.createCriteria().andAccountEqualTo(person.getAccount());
        int insert=-1;
        if ( personDAO.selectByExample(example).isEmpty()) {
            insert = personDAO.insert(person);
        }
        List<Person> peoples=null;
        if (insert==1){
            peoples = personDAO.selectByExample(example);
        }
        return peoples!=null&&peoples.size()==1?new ResultBody<Person>().setMsg("成功").setCode(200).setBody(peoples.get(0))
                :new ResultBody<>().setMsg("已有该账号").setCode(500);
    }

    @Override
    public Person validLogin(String account, String password) {
        Person person = personDAO.findByAccount(account);
       if (person!=null&&person.getPassword().equals(password)){
            return person;
       }
        return null;
    }

    @Override
    public ResultBody change(Person person) {
        int i = personDAO.updateByPrimaryKeySelective(person);

        return i==1?ResultBody.success:ResultBody.error;
    }

    @Override
    public ResultBody changeByPassword(Person person,String oldPw) {

        PersonExample example=new PersonExample();
        example.createCriteria().andPasswordEqualTo(oldPw).andIdEqualTo(person.getId());
        int i =personDAO.updateByExampleSelective(person,example);
        return i==1?ResultBody.success:ResultBody.error;
    }
}
