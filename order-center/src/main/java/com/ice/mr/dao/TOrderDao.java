package com.ice.mr.dao;

import com.ice.mr.base.IBaseDao;
import com.ice.mr.info.TOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author  system
 */
@Mapper
@Repository
public interface TOrderDao extends IBaseDao<TOrderInfo> {

    //--CustomBegin***///
    Integer deleteByOrderNo(@Param("orderNo") String orderNo);
    //--CustomEnd*****///
}

