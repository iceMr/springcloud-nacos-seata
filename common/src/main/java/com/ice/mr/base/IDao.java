package com.ice.mr.base;

import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 所有Dao基类
 * @author maguangming
 *
 * @param <T>
 */
public interface IDao<T> {
    /**
     * 根据id查询对象
     */
    public T findById(@Param("id") Serializable id);

    /**
     * 根据联合主键查询对象
     */
    public T findById(java.util.Map<String, String> ids);

    /**
     * 根据实体查询对象
     */
    public List<T> findByParam(@Param("obj") T param);

     /**
     * 根据map查询对象并分页
     */
    public List<T> findByParamForPage(@Param("obj") PageParameter param);


    /**
     * 添加对象
     * @param param 对象
     * @return 对象id
     */
    public Integer insert(T param);

    /**
     * 更新对象
     * @param param 对象
     * @param versionId 版本号
     * @return 影响条数
     */
    public Integer update(@Param("obj") T param,@Param("versionId") Integer versionId);

    /**
     * 根据主键删除对象
     * @param id 主键
     * @return 影响条数
     */
    public Integer deleteById(@Param("id") Serializable id);
    
    /**
     * 根据主键删除对象
     * @return 影响条数
     */
    public Integer deleteById(java.util.Map<String, String> keys);
    
    /**
     * 更新封存
     * @param id 主键
     * @param val 值
     * @param versionId 版本号（可空）
     * @return
     */
    public Integer updateSealFlag(@Param("id")Serializable id,@Param("val")Boolean val,@Param("versionId") Integer versionId);
  
}
