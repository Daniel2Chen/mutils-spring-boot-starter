package cn.minsin.core.web.receive;

import cn.minsin.core.tools.date.DateUtil;
import cn.minsin.core.tools.date.DefaultDateFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.Date;

/**
 * 用于表格时间段搜索
 * @Author: minton.zhang
 * @since: 0.0.8.RELEASE
 */
@Getter
public class DateCondition {

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间 yyyy-MM-dd")
    private Date beginDate;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间  yyyy-MM-dd")
    private Date endDate;

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate == null ? null : DateUtil.getBeginOfDay(beginDate, DefaultDateFormat.yyyy_MM_dd);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : DateUtil.getEndOfDay(endDate, DefaultDateFormat.yyyy_MM_dd);
    }
}
