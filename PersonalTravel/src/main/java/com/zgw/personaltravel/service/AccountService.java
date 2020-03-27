package com.zgw.personaltravel.service;


import com.zgw.personaltravel.entity.Person;
import com.zgw.personaltravel.entity.ResultBody;

public interface AccountService {


    ResultBody<Person> insertPerson(Person person);

    Person validLogin(String account, String password);

    ResultBody change(Person person);

    ResultBody changeByPassword(Person person,String oldPw);
}
