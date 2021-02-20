package com.moon.mongo;

import com.moon.mongo.repository.UserRepositoryImpl;
import com.moon.mongo.repository.facade.UserRepository;
import com.moon.mongo.repository.po.UserPO;
import org.apache.commons.lang3.RandomUtils;
import org.bson.types.Decimal128;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {
    @Autowired
    UserRepositoryImpl userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void addUser(){
        for(int i=20; i<30;i++){
            UserPO userPO = new UserPO();
            userPO.setId(String.valueOf(i));
            userPO.setName("user");
            userPO.setSalary(new Decimal128(i*100));
            userPO.setSex(RandomUtils.nextInt(0,1));
            userRepository.save(userPO);
        }
    }

    @Test
    public void testPageQuery1(){
        PageImpl<UserPO> userPOPage = userRepository.findPageUsers2("user",0,5);
        if (!userPOPage.isEmpty()){
            userPOPage.get().map(UserPO::getId).forEach(System.out::println);
        }
    }

}
