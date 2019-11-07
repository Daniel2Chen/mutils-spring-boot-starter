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


//
//    public <T> IPage<T> toPage(Class<T> clazz, boolean needCount) {
//        return new Page<T>().setSize(pageSize).setCurrent(page).setSearchCount(needCount).setOptimizeCountSql(false);
//    }
//
//    /**
//     * 转换成制定对象的page
//     *
//     * @param <T>
//     * @param source
//     * @param data
//     * @return
//     */
//    public <T> Page<T> convertPage(IPage<?> source, List<T> data) {
//        return new Page<T>(source.getCurrent(), source.getSize(), source.getTotal()).setRecords(data == null ? new ArrayList<>(0) : data);
//    }
}
