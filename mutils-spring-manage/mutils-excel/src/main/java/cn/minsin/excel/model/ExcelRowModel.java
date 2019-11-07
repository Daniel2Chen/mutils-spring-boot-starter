package cn.minsin.excel.model;

import cn.minsin.core.rule.AbstractModelRule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ExcelRowModel extends AbstractModelRule {

    /**
     *
     */
    private static final long serialVersionUID = 7096662531755822709L;

    /**
     * 行下标
     */
    private int rowIndex;

    /**
     * key为列下标 value为改该列的值
     */
    @Setter(AccessLevel.NONE)
    private Map<Integer, Object> cells =new HashMap<>(10);

    /**
     * 赋值
     *
     * @param data
     */
    public void setCells(Map<Integer, Object> data) {
        cells.putAll(data);
    }

}
