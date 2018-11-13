package com.pjmike.jpa.entity;

import javax.persistence.*;

/**
 * 网站
 *
 * @author pjmike
 * @create 2018-10-03 9:31
 */
@Entity
@Table(name = "website")
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String web_name;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Author author;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Article article;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeb_name() {
        return web_name;
    }

    public void setWeb_name(String web_name) {
        this.web_name = web_name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
