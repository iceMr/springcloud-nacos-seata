package com.ice.mr.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Administrator
 */
@ApiModel(value="PageBean",description="分页信息")
public class PageBean<T> {
    @ApiModelProperty(value="total",hidden=false,notes="总条数")
    private Long total;
    @ApiModelProperty(value="rows",hidden=false,notes="数据集合")
    private List<T> rows ;
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
