package com.ice.mr.base;

import java.io.Serializable;
import java.util.List;

/**
 * 可作为service的基类自动完成基本的增删改查
 * @author maguangming
 *
 * @param <M>
 */
public abstract  class IService<M extends Serializable> {
    
    protected final  String redisSelectFlag="select";
    protected final  String redisUpdateFlag="update";
    protected final  String redisDeleteFlag="delete";
    
    private IDao<M> idao;
    public void setRepository(IDao<M> repository) {
        this.idao = repository;  
    }  
    

  /**
  * 查询表中 符合入参的集合
  * @param findInfo 入参,查询条件
  * @return List<M> 出参,符合条件的结果集
  */
 public List<M> findByParam(M findInfo) {
     return idao.findByParam(findInfo);
 }
   
   /**
  * 查询Dictionary表中 符合入参的集合
  * @param findInfo 入参,查询条件
  * @return List<M> 出参,符合条件的结果集
  */
 public List<M> findByParamForPage(PageParameter findInfo) {
     return idao.findByParamForPage(findInfo);
 }
 
 /**
  * 新增对象
  * @param info 对象
  * @return 对象id
  */
 public Integer add(M info) {
     return idao.insert(info);
 }   
 
 /**
  * 更新对象
  * @param info 对象(主键必须非空)
  * @param versionId 版本号(可空)
  * @return 影响条数
  */
 public Integer update(M info,Integer versionId) {
     int result= idao.update(info,versionId);
       if(result!=0){
           handleRedis(info,redisUpdateFlag);
       }
       return result;
 }   
 /**
  * 根据主键删除
  * @param id 对象(主键)
  * @return 
  */
 public Integer delete(Serializable id) {
     int result= idao.deleteById(id);
       if(result!=0){
           handleRedis(id,redisDeleteFlag);
       }
       return result;
 }
 
 /**
  * 根据联合主键删除
  * @param  ids map(主键)
  * @return 对象id
  */
 public Integer delete(java.util.Map<String, String> ids) {
     return idao.deleteById(ids);
 }
   
 /**
  * 根据id查询对象
  * @param id 对象主键
  * @return 对象本身
  */
 public M findById(Serializable id) {
     M result= null;
       Object redisResult=handleRedis(id,redisSelectFlag);
       if(redisResult==null){
           result=idao.findById(id);
       }
       else{
           result=(M)redisResult;
       }
       return result;
 }
 
 /**
  * 根据联合主键查询对象
  * @param ids map对象主键
  * @return 对象本身
  */
 public M findById(java.util.Map<String, String> ids) {
     return idao.findById(ids);
 }
 
 /**
  * 更新封存标识
  * @param id 主键
  * @param val 值（true/false）
  * @param versionId 版本号(可空)
  * @return
  */
 public Integer sealFlag (Serializable id,Boolean val,Integer versionId){
     int result= idao.updateSealFlag(id,val,versionId);
     if(result!=0){
         handleRedis(id,"delete");
     }
     return result;
 }
 
 /**
  * 处理redis的方法，子类需要重写方法
  * @param obj: vo 或者id
  * @param flag ：select update insert delete
  * @return
  */
 protected Object handleRedis(Object obj,String flag){
     return null;
 }
    
}
