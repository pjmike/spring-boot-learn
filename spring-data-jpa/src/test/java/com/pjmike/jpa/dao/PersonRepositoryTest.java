package com.pjmike.jpa.dao;

import com.pjmike.jpa.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);
    @Autowired
    private PersonRepository personRepository;
    @Test
    @Transactional
    @Rollback
    public void testPerson() {
        Person person = new Person();
        person.setUsername("pjmike_456");
        person = personRepository.save(person);
        logger.info("添加成员: {}",person);
        Person user = personRepository.findByUsername("pjmike_456");
        logger.info("条件查询: {}",user);
    }
}