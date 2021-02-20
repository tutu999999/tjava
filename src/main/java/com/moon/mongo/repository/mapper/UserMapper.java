package com.moon.mongo.repository.mapper;

import com.moon.mongo.repository.po.UserPO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMapper extends MongoRepository<UserPO,String> {

}
