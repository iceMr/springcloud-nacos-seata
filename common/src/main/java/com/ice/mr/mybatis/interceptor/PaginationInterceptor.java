package com.ice.mr.mybatis.interceptor;


import com.ice.mr.base.PageParameter;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class }) })
public class PaginationInterceptor implements Interceptor {
	private static final Log logger = LogFactory.getLog(PaginationInterceptor.class);
    protected static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    protected static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    protected static String defaultDialect = "mysql";
    private static String defaultPageSqlId = ".*Page$";
    private static String dialect = "";
    private static String pageSqlId = "";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        }
        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        dialect = configuration.getVariables().getProperty("dialect");
        if (null == dialect || "".equals(dialect)) {
            logger.warn("Property dialect is not setted,use default 'mysql' ");
            dialect = defaultDialect;
        }
        pageSqlId = configuration.getVariables().getProperty("pageSqlId");
        if (null == pageSqlId || "".equals(pageSqlId)) {
            logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
            pageSqlId = defaultPageSqlId;
        }
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
        if (mappedStatement.getId().matches(pageSqlId)) {
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null) {
                throw new NullPointerException("parameterObject is null!");
            } 
            else {
            	Object o=metaStatementHandler.getValue("delegate.boundSql.parameterObject");
            	PageParameter page;
            	if(o instanceof PageParameter){
            		 page = (PageParameter) metaStatementHandler.getValue("delegate.boundSql.parameterObject");
            		 if(page.getSearch()!=null){
            			    Map<String,String> paramMab=page.getSearch();  
            	            for(Entry<String,String> entry:paramMab.entrySet()){
            	                String key=entry.getKey();
            	                String val =paramMab.get(key);  
            	                if(val!=null &&val instanceof String&&(val.toString().contains("%")||val.toString().contains("_"))){
            	                    val=AntiSqlInterceptor.antiSql(val.toString());
            	                    paramMab.put(key.toString(), val);  
            	                }             
            	            }     
            		 }
            		 if(page.getSelect()!=null){
         			    Map<String,Object> paramMab=page.getSelect();  
         	            for(Entry<String,Object> entry:paramMab.entrySet()){
         	            	String key=entry.getKey();
         	            	Object val =paramMab.get(key);  
         	                if(val!=null &&val instanceof String&&(val.toString().contains("%")||val.toString().contains("_"))){
         	                	val=AntiSqlInterceptor.antiSql(val.toString());
         	                    paramMab.put(key, val);  
         	                }             
         	            }     
         		 }
            	}else{
            		 page = (PageParameter) ((Map)(metaStatementHandler.getValue("delegate.boundSql.parameterObject"))).get("obj");
            	}
                String sql = boundSql.getSql();
                String pageSql = buildPageSql(sql, page);
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
                Connection connection = (Connection) invocation.getArgs()[0];
                setPageParameter(sql, connection, mappedStatement, boundSql, page);
            }
        }
        return invocation.proceed();
    }

    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,BoundSql boundSql, PageParameter page) {
        String countSql = "select count(0) from (" + sql + ") as total";
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            page.setTotalCount(totalCount);
            int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
            page.setTotalPage(totalPage);

        } catch (SQLException e) {
            logger.error("Ignore this exception", e);
        } finally {
            try {
                if(rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
            try {
                if(countStmt!=null){
                    countStmt.close();
                }
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
        }

    }

    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
            Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    private String buildPageSql(String sql, PageParameter page) {
        if (page != null) {
            StringBuilder pageSql = new StringBuilder();
            if ("mysql".equals(dialect)) {
                pageSql = buildPageSqlForMysql(sql, page);
            } else if ("oracle".equals(dialect)) {
                pageSql = buildPageSqlForOracle(sql, page);
            } else if ("mssql".equals(dialect)) {
                pageSql = buildPageSqlForMssql(sql, page);
            } else {
                return sql;
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    public StringBuilder buildPageSqlForMysql(String sql, PageParameter page) {
        StringBuilder pageSql = new StringBuilder(100);
        String beginrow = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());
        pageSql.append(sql);
        pageSql.append(" limit " + beginrow + "," + page.getPageSize());
        return pageSql;
    }
    public StringBuilder buildPageSqlForMssql(String sql, PageParameter page) {
        int beginrow =  (page.getCurrentPage() - 1) * page.getPageSize();
        int endrow =  page.getCurrentPage() * page.getPageSize();
        StringBuilder pageSql = new StringBuilder(sql.length() + 100);
        pageSql.append("select * from (Select ROW_NUMBER() OVER (order by "+page.getOrderField()+" "+page.getOrderType()+") as pos,* from (");
        pageSql.append(sql);  
        pageSql.append(") temp) as sp where pos between "+(beginrow+1)+" and "+endrow+"");  
        return pageSql;  
        
         
    }

    public StringBuilder buildPageSqlForOracle(String sql, PageParameter page) {
        StringBuilder pageSql = new StringBuilder(100);
        String beginrow = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());
        String endrow = String.valueOf(page.getCurrentPage() * page.getPageSize());

        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
        pageSql.append(sql);
        pageSql.append(" ) temp where rownum <= ").append(endrow);
        pageSql.append(") where row_id > ").append(beginrow);
        return pageSql;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }
    @Override
    public void setProperties(Properties properties) {
    }

}
