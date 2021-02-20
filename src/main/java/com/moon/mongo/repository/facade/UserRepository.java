package com.moon.mongo.repository.facade;

import com.moon.infrastructure.core.util.PageInfo;
import com.moon.mongo.repository.po.UserPO;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    PageImpl<UserPO> findPageUsers(String userName, int pageIndex, int pageSize);

    PageImpl<UserPO> findPageUsers2(String userName, int pageIndex, int pageSize);

    UserPO save(UserPO userPO);
}
