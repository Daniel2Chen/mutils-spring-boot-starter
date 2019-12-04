package cn.minsin.core.web.receive;

import cn.minsin.core.tools.NumberUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @Author: minton.zhang
 * @Date: 2019/8/2 11:09
 */
@Getter
public class PageCondition {


    @ApiModelProperty("页码 默认1")
    private int page = 1;

    @ApiModelProperty("显示条数 默认10")
    private int pageSize = 10;

    public void setPage(String page) {
        int i = NumberUtil.toInt(page, 1);
        this.page = Math.max(i, 1);
    }

    public void setPageSize(String pageSize) {
        int i = NumberUtil.toInt(pageSize, 10);
        this.pageSize = i < 1 ? 10 : i;
    }
}
