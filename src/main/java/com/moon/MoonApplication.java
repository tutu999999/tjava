package com.moon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {
        // mongodb
        DataSourceAutoConfiguration.class
        //,MongoAutoConfiguration.class, MongoDataAutoConfiguration.class
        })
@ComponentScan("com.moon")
@EnableMongoRepositories
public class MoonApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoonApplication.class, args);
    }
}
