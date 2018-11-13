package com.pjmike.jpa.repository;

import com.pjmike.jpa.entity.Article;
import com.pjmike.jpa.entity.Author;
import com.pjmike.jpa.entity.Website;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private WebSiteRepository webSiteRepository;
    @Test
    public void save_author_and_artilce(){
        Author author = new Author();
        author.setUsername("pjmike");
        author.setAvatar("http://avatar.jpg");
        authorRepository.save(author);
        Article article1 = new Article();
        article1.setArticle("title1");
        article1.setContent("content2");
        articleRepository.save(article1);
    }

    @Test
    public void save_website() {
        Optional<Article> article = articleRepository.findById(1);
        Article article1 = article.get();
//        System.out.println("article: " + article1);
        Optional<Author> author = authorRepository.findById(1);
        Author author1 = author.get();
//        System.out.println("author: "+author1);
        Website website = new Website();
        website.setWeb_name("web_name");
        website.setArticle(article1);
        website.setAuthor(author1);
        article1.getWebsites().add(website);
        author1.getWebsites().add(website);
        webSiteRepository.save(website);
    }

}