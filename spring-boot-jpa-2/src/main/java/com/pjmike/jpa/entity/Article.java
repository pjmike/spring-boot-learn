package com.pjmike.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章
 *
 * @author pjmike
 * @create 2018-10-03 9:22
 */
@Data
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String article;
    private String content;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "article",targetEntity = Website.class,fetch = FetchType.EAGER)
    private List<Website> websites = new ArrayList<>();
}
