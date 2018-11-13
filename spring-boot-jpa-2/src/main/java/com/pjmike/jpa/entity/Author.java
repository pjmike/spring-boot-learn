package com.pjmike.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 作者
 *
 * @author pjmike
 * @create 2018-10-03 9:18
 */
@Data
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String avatar;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "author",targetEntity = Website.class,fetch = FetchType.EAGER)
    private List<Website> websites = new ArrayList<>();
}
