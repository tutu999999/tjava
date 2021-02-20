package com.moon.mongo.repository.persistence;

import com.moon.infrastructure.core.util.PageInfo;
import com.moon.mongo.repository.facade.UserRepository;
import com.moon.mongo.repository.mapper.UserMapper;
import com.moon.mongo.repository.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    UserMapper userMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 分页使用count
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PageImpl<UserPO> findPageUsers(String name, int pageIndex, int pageSize) {
        Query query = Query.query(
                Criteria.where("name").is(name));
        // 每页五个
        final Pageable pageable = PageRequest.of(pageIndex, pageSize); // get 5 profiles on a page
        query.with(pageable);
        // 排序
        query.with( Sort.by(Sort.Direction.ASC, "id"));
        // 查询总数
        int count = (int) mongoOperations.count(query, UserPO.class,"uesr");
        List<UserPO> items = mongoOperations.find(query, UserPO.class);
        return (PageImpl<UserPO>) PageableExecutionUtils.getPage(items, pageable, () -> count);
    }

    /**
     * 分页不使用count（性能更优)
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PageImpl<UserPO> findPageUsers2(String name, int pageIndex, int pageSize) {
        Query query = Query.query(
                Criteria.where("name").is(name));
        // 每页五个  pageIndex从0开始
        final Pageable pageable = PageRequest.of(pageIndex, pageSize); // get 5 profiles on a page
        query.with(pageable);
        // 排序
        query.with( Sort.by(Sort.Direction.DESC, "id"));
        List<UserPO> list = mongoOperations.find(query, UserPO.class);
        return (PageImpl<UserPO>) PageableExecutionUtils.getPage(list, pageable, () -> 0);
    }

    @Override
    public UserPO save(UserPO userPO) {
        return userMapper.save(userPO);
    }
}
