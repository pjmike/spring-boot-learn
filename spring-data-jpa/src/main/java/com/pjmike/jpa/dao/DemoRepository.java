package com.pjmike.jpa.dao;

import com.pjmike.jpa.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pjmike
 * @create 2018-09-10 10:35
 */
@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {

}
