package cn.minsin.excel.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.AbstractConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * excel配置文件
 *
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = MutilsModelConstant.MUTILS_PREFIX + ".excel")
public class MutilsExcelProperties extends AbstractConfig {

    /**
     * excel统一错误模板路径
     */
    private String errorTemplatePath;

    /**
     * 开始解析的sheet下标
     */
    private int errorTemplateSheetIndex = 0;

    /**
     * 开始解析的row下标
     */
    private int errorTemplateRowIndex = 0;

    /**
     * 开始解析的cell下标
     */
    private int errorTemplateCellIndex = 0;

    /**
     * 输出文件名
     */
    private String errorTemplateExportName = "错误概要";

    @NestedConfigurationProperty
    private InputStream inputStream;

    @Override
    protected void checkConfig() {
        slog.info("Required for initialization errorTemplatePath,and this file must exists.");
        MutilsException.throwException(errorTemplatePath == null, "errorTemplatePath must not be null.");
        if (errorTemplatePath.startsWith("/")) {
            InputStream inStream = MutilsExcelProperties.class.getClassLoader().getResourceAsStream(errorTemplatePath.replaceFirst("/", ""));
            MutilsException.throwException(inStream == null, "errorTemplatePath not exists.");
            this.inputStream = inStream;
        } else {
            File file = new File(errorTemplatePath);
            MutilsException.throwException(!file.exists(), "errorTemplatePath not exists.");
            try {
                this.inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
