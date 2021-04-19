
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
public class TAccountInfo implements Serializable
{
	
	private Integer id;   
	private String userId;   
	private Double amount;   
    
    public TAccountInfo(){}
   
    
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
           .append("UserId",getUserId())
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
		if(obj instanceof TAccountInfo == false){ return false;}
		if(this == obj) { return true; }
		TAccountInfo other = (TAccountInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
    
    //--CustomBegin***/////--CustomEnd*****///
}

