package com.pjmike.beanutils.model;

/**
 * @author pjmike
 * @create 2018-09-10 15:48
 */
public class PersonDest {
    private Integer id;
    private String username;
    private int age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonDest{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
