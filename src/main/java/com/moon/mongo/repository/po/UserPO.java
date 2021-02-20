package com.moon.mongo.repository.po;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "user")
public class UserPO {
    @Id
    private String id;

    private String name;

    private Decimal128 salary;

    private Integer sex;

    private LocalDate birthDary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Decimal128 getSalary() {
        return salary;
    }

    public void setSalary(Decimal128 salary) {
        this.salary = salary;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDary() {
        return birthDary;
    }

    public void setBirthDary(LocalDate birthDary) {
        this.birthDary = birthDary;
    }
}
