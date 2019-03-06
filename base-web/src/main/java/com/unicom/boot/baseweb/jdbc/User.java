package com.unicom.boot.baseweb.jdbc;

/**
 * @author dengh
 */
public class User {

    private Integer id;

    private String name;

    private Integer age;

    public Integer getId(int id) {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
