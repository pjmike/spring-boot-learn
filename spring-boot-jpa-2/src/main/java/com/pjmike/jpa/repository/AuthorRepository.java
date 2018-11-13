package com.pjmike.jpa.repository;

import com.pjmike.jpa.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pjmike
 * @create 2018-10-03 10:17
 */
public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
