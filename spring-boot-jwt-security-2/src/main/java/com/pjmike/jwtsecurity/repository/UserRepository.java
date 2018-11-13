package com.pjmike.jwtsecurity.repository;

import com.pjmike.jwtsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pjmike
 * @create 2018-10-04 15:51
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
