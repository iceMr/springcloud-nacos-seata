package com.ice.mr.service;

import com.ice.mr.dao.TAccountDao;
import io.seata.core.context.RootContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private TAccountDao tAccountDao ;

    @Transactional(rollbackFor = Exception.class)
    public Boolean deductAccount(String userId, double amount) {
        System.out.println("开始全局事务，XID = " + RootContext.getXID());
        tAccountDao.deductAccount(userId,amount);
        return true;
    }
}
