package cn.minsin.excel.model;

import cn.minsin.core.rule.AbstractModelRule;
import lombok.Getter;
import lombok.Setter;

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
    private Map<Integer, Object> cells;

}
