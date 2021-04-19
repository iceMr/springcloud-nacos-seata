package com.ice.mr.mybatis.interceptor;

import com.ice.mr.base.PageParameter;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.text.DateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }) })
public class AntiSqlInterceptor implements Interceptor {
 
    private Properties properties;
 
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        Object returnValue = null;
        String sql = getSql(configuration, boundSql);
        returnValue = invocation.proceed();
        return returnValue;
    }
 
    public static String getSql(Configuration configuration, BoundSql boundSql) {
        return showSql(configuration, boundSql);
    }
 
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } 
        else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } 
        else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
 
        }
        return value;
    }
 
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        return modifyLikeSql(sql,parameterObject);
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
 
    @Override
    public void setProperties(Properties properties0) {
        this.properties = properties0;
    }
    
    public static String modifyLikeSql_todo(String sql,Object parameterObject){
        if(parameterObject instanceof PageParameter){
            if(!sql.toLowerCase().contains("like")){
                return sql;
            }
            String reg="\\s\\w+\\sLIKE\\s*('%'\\s*\\|{2}\\s*)?(#\\{\\w+\\})(\\s*\\|{2}\\s*'%')?";
            Pattern pattern = Pattern.compile(reg,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(sql);
            
            List<String> replaceEscape=new ArrayList<String>();
            List<String> replaceFiled=new ArrayList<String>();
            
            while(matcher.find()){
                replaceEscape.add(matcher.group());
                 int n = matcher.groupCount();  
                 for (int i = 0; i <= n; i++)
                 {  
                    String  output = matcher.group(i);  
                    if(2==i&&output!=null)
                    {
                        replaceFiled.add(output.trim());
                    }
                 }  
               }
            for(String s:replaceEscape){
                sql=sql.replace(s, s+" ESCAPE '/' ");
            }
            HashMap<String,String> paramMab=(HashMap<String, String>)((PageParameter)parameterObject).getSearch();
            for(String s:replaceFiled) {
                String key=s.replace("#{", "").replace("}", "");
                String val =paramMab.get(key);
                if(val!=null &&val instanceof String&&(val.toString().contains("%")||val.toString().contains("_"))){
                    val=val.toString().replaceAll("%", "/%").replaceAll("_", "/_");
                    paramMab.put(key.toString(), val);
                }           
            }   
            return sql;   
        }
        else{
            return sql;         
        }
        
    }
    
    public static String modifyLikeSql(String sql,Object parameterObject){
        if(parameterObject instanceof HashMap){  
        }
        else if(parameterObject instanceof String){
        	parameterObject=parameterObject.toString().replaceAll("%", "\\\\%").replaceAll("_", "\\\\_"); 
        }
        else{  
            return sql;           
        }  
        if(!sql.toLowerCase().contains("like")){
            return sql;
        }
        HashMap<String,Object> paramMab=(HashMap)parameterObject;
        for(Entry<String,Object> entry:paramMab.entrySet()) {
            String key=entry.getKey();
            Object val =paramMab.get(key);  
            if(val!=null &&val instanceof String&&(val.toString().contains("%")||val.toString().contains("_")))  
            {  
                val=antiSql(val.toString());
                paramMab.put(key.toString(), val);  
            }             
        }     
        return sql;     
    }  
    public static String antiSql(String sql){
    	sql=sql.replaceAll("%", "\\\\%");
    	if(sql.startsWith("_")){
    		sql="\\_"+sql.substring(1);
    	}
    	return sql;
    }
}