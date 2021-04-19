package com.ice.mr.info;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author system
 */
@SuppressWarnings("serial")
public class TOrderInfo implements Serializable
{
	
	private Integer id;
	private String orderNo;
	private String userId;
	private String commodityCode;
	private Integer count;   
	private Double amount;
    
    public TOrderInfo(){}
   
    
    /**
     * 
     */ 	
	public Integer getId(){
        return id;
    }
    
     /**
     * 
     */ 	
    public void setId(Integer id){
        this.id=id;
    }
    
    /**
     * 
     */ 	
	public String getOrderNo(){
        return orderNo;
    }
    
     /**
     * 
     */ 	
    public void setOrderNo(String orderNo){
        this.orderNo=orderNo;
    }
    
    /**
     * 
     */ 	
	public String getUserId(){
        return userId;
    }
    
     /**
     * 
     */ 	
    public void setUserId(String userId){
        this.userId=userId;
    }
    
    /**
     * 
     */ 	
	public String getCommodityCode(){
        return commodityCode;
    }
    
     /**
     * 
     */ 	
    public void setCommodityCode(String commodityCode){
        this.commodityCode=commodityCode;
    }
    
    /**
     * 
     */ 	
	public Integer getCount(){
        return count;
    }
    
     /**
     * 
     */ 	
    public void setCount(Integer count){
        this.count=count;
    }
    
    /**
     * 
     */ 	
	public Double getAmount(){
        return amount;
    }
    
     /**
     * 
     */ 	
    public void setAmount(Double amount){
        this.amount=amount;
    }
    
    @Override
    public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
           .append("Id",getId())
           .append("OrderNo",getOrderNo())
           .append("UserId",getUserId())
           .append("CommodityCode",getCommodityCode())
           .append("Count",getCount())
           .append("Amount",getAmount())

			.toString();
            
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TOrderInfo == false){ return false;}
		if(this == obj) { return true; }
		TOrderInfo other = (TOrderInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
    
    //--CustomBegin***/////--CustomEnd*****///
}

