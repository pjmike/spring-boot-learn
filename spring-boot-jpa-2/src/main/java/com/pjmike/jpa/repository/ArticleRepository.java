package com.pjmike.jpa.repository;

import com.pjmike.jpa.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author pjmike
 * @create 2018-10-03 10:18
 */

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
