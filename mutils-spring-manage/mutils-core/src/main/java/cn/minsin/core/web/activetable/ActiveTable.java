package cn.minsin.core.web.activetable;

import lombok.Getter;

import java.util.List;

/**
 * 自动获取表头
 *
 * @author: minton.zhang
 * @date: 2019/10/14 19:27
 */
@Getter
public class ActiveTable {

    public ActiveTable(Object data, Class<?> clazz) {
        this.tableData = data;
        this.tableHeaders = ActiveTableHeader.loadClass(clazz).getTableHeaders();
    }

    public ActiveTable(Object data, ActiveTableHeader keyData) {
        this.tableData = data;
        this.tableHeaders = keyData.getTableHeaders();
    }

    private Object tableData;

    private List<ActiveTableHeader> tableHeaders;
}
