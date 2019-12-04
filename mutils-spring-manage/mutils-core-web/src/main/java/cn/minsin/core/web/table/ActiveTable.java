package cn.minsin.core.web.table;

import cn.minsin.core.tools.ModelUtil;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 自动获取表头
 *
 * @author: minton.zhang
 * @date: 2019/10/14 19:27
 */

public class ActiveTable {

//    public ActiveTable(ActiveTableHeader keyData) {
//        this.tableHeaders = keyData.getTableHeaders();
//    }
    @Getter
    private List<Collection<ActiveTableData>> tableData = new CopyOnWriteArrayList<>();
    @Getter
    private List<ActiveTableHeader> tableHeaders;

    private final transient Map<String, ActiveTableData> tempMap =new ConcurrentHashMap<>();

    public ActiveTable add(String columnKey, Object value) {
        tempMap.put(columnKey, new ActiveTableData(columnKey, value));
        return this;
    }

    public ActiveTable add(Object model) {
        Set<Field> allFieldsAndFilter = ModelUtil.getAllFieldsAndFilter(model);
        for (Field field : allFieldsAndFilter) {
            try {
                field.setAccessible(true);
                Object o = field.get(model);
                tempMap.put(field.getName(), new ActiveTableData(field.getName(), o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 调用者 必须要在一个对象结束转换时调用该方法
     */
    public void done() {
        Collection<ActiveTableData> temp = new CopyOnWriteArrayList<>();
        tableHeaders.forEach(e -> {
            String columnKey = e.getColumnKey();
            ActiveTableData activeTableData1 = tempMap.containsKey(columnKey) ? tempMap.get(columnKey) : new ActiveTableData(columnKey, "无");
            temp.add(activeTableData1);
        });
        tableData.add(temp);
        //按照header的顺序
        tempMap.clear();
    }
}
