package com.pjmike.jwtsecurity.repository;

import com.pjmike.jwtsecurity.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pjmike
 * @create 2018-10-04 15:56
 */
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

}
