package com.pjmike.jpa.dao;

import com.pjmike.jpa.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    @Test
    public void save() {
        Person person = new Person();
        person.setContent("hello world");
        person = personRepository.save(person);
        System.out.println("Person id: "+person.getId()+", Person content: "+person.getContent());
    }
}