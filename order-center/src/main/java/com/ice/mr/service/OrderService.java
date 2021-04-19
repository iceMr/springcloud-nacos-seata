package com.ice.mr.service;

import com.ice.mr.dao.TOrderDao;
import com.ice.mr.info.TOrderInfo;
import ice.mr.pojo.UserOrder;
import io.seata.core.context.RootContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import com.ice.mr.util.UniqueSeq;

@Service
public class OrderService {

    @Resource
    private TOrderDao tOrderDao ;

    @Transactional(rollbackFor ={Exception.class} )
    public Boolean addOrder(UserOrder userOrder) {
        System.out.println("开始全局事务，XID = " + RootContext.getXID());
        TOrderInfo tOrderInfo =new TOrderInfo();
        BeanUtils.copyProperties(userOrder, tOrderInfo);
        tOrderInfo.setOrderNo(UniqueSeq.shortUUID());
        tOrderDao.insert(tOrderInfo);
        return true;
    }
}
