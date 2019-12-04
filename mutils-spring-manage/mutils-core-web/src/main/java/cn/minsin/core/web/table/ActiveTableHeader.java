package cn.minsin.core.web.table;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态表格表头
 *
 * @author: minton.zhang
 * @since: 2019/10/15 17:52
 */
public class ActiveTableHeader extends BaseActiveTable {

    @Getter(AccessLevel.PACKAGE)
    private transient List<ActiveTableHeader> tableHeaders;

    @Getter
    private String showName;

    private ActiveTableHeader(int size) {
        tableHeaders = new ArrayList<>(size);
    }

    private ActiveTableHeader(String columnKey, String showName) {
        this.showName = showName;
        super.setColumnKey(columnKey);
    }

    public ActiveTableHeader add(String columnKey, String showName) {
        tableHeaders.add(new ActiveTableHeader(columnKey, showName));
        return this;
    }

    public ActiveTableHeader add(Class<?> clazz) {
        Assert.notNull(clazz, "动态表格必须传入Class");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Th annotation = field.getAnnotation(Th.class);
            if (annotation != null) {
                this.add(field.getName(), annotation.value());
            }
        }
        return this;
    }

    public static ActiveTableHeader init(int size) {
        return new ActiveTableHeader(size);
    }

    public static ActiveTableHeader init() {
        return new ActiveTableHeader(0);
    }



}
