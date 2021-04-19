package com.ice.mr.dao;

import com.ice.mr.base.IBaseDao;
import com.ice.mr.info.TAccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author  system
 */
@Mapper
@Repository
public interface TAccountDao extends IBaseDao<TAccountInfo> {
    //--CustomBegin***///
    Integer deductAccount(@Param("userId") String userId,@Param("amount") double amount);
    Integer receiveAccount(@Param("userId") String userId,@Param("amount") double amount);
    //--CustomEnd*****///
}

