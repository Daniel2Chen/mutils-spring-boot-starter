package cn.minsin.core.web.activetable;

import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态表格表头
 *
 * @author: minton.zhang
 * @since: 2019/10/15 17:52
 */
public class ActiveTableHeader {


    @Getter
    private transient List<ActiveTableHeader> tableHeaders;

    @Getter
    private String columnKey, showName;

    private ActiveTableHeader(int size) {
        tableHeaders = new ArrayList<>(size);
    }

    private ActiveTableHeader(String columnKey, String showName) {
        this.columnKey = columnKey;
        this.showName = showName;
    }

    public ActiveTableHeader add(String columnKey, String showName) {
        tableHeaders.add(new ActiveTableHeader(columnKey, showName));
        return this;
    }

    static ActiveTableHeader loadClass(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        ActiveTableHeader activeTableHeader = new ActiveTableHeader(fields.length);
        for (Field field : fields) {
            Th annotation = field.getAnnotation(Th.class);
            if (annotation != null) {
                activeTableHeader.add(field.getName(), annotation.value());
            }
        }
        return activeTableHeader;
    }

    public static ActiveTableHeader init(int size) {
        return new ActiveTableHeader(size);
    }

    public static ActiveTableHeader init() {
        return new ActiveTableHeader(0);
    }
}
