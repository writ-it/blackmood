package com.cq.black.service.person.impl;

import com.cq.black.dao.person.PersonMapper;
import com.cq.black.entity.person.Person;
import com.cq.black.service.person.PersonService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private  PersonMapper personMapper;
    @Override
    public int insertPerson() {
        Person person=new Person();
        person.setName("大傻逼");
        person.setAge(78);
        return personMapper.insert(person);
    }
}
