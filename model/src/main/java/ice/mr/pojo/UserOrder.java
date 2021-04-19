package ice.mr.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UserOrder",description="订单对象")
public class UserOrder {

    @ApiModelProperty(value="用户ID",hidden=false)
    private String userId;
    @ApiModelProperty(value="商品编码",hidden=false)
    private String commodityCode;
    @ApiModelProperty(value="购买数量",hidden=false)
    private Integer count;
    @ApiModelProperty(value="总金额",hidden=false)
    private Double  amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
