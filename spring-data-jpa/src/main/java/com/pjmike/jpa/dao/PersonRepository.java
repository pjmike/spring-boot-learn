package com.pjmike.jpa.dao;

import com.pjmike.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pjmike
 * @create 2018-08-07 15:13
 */
@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByUsername(String username);
}
