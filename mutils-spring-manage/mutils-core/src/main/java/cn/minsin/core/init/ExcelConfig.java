package cn.minsin.core.init;

import java.io.File;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.tools.StringUtil;

/**
 * 	excel配置文件
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
public class ExcelConfig extends AbstractConfig {

	/**
	 * 	excel统一错误模板路径	
	 */
	private String errorTemplatePath;

	/**
	 * 	开始解析的sheet下标
	 */
	private int errorTemplateSheetIndex = 0;

	/**
	 * 	开始解析的row下标
	 */
	private int errorTemplateRowIndex = 0;

	/**
	 * 	开始解析的cell下标
	 */
	private int errorTemplateCellIndex = 0;

	/**
	 * 	输出文件名
	 */
	private String errorTemplateExportName = "错误概要";

	public String getErrorTemplateExportName() {
		return errorTemplateExportName;
	}

	public void setErrorTemplateExportName(String errorTemplateExportName) {
		this.errorTemplateExportName = errorTemplateExportName;
	}

	public int getErrorTemplateSheetIndex() {
		return errorTemplateSheetIndex;
	}

	public void setErrorTemplateSheetIndex(int errorTemplateSheetIndex) {
		this.errorTemplateSheetIndex = errorTemplateSheetIndex;
	}

	public int getErrorTemplateRowIndex() {
		return errorTemplateRowIndex;
	}

	public void setErrorTemplateRowIndex(int errorTemplateRowIndex) {
		this.errorTemplateRowIndex = errorTemplateRowIndex;
	}

	public int getErrorTemplateCellIndex() {
		return errorTemplateCellIndex;
	}

	public void setErrorTemplateCellIndex(int errorTemplateCellIndex) {
		this.errorTemplateCellIndex = errorTemplateCellIndex;
	}

	public String getErrorTemplatePath() {
		return errorTemplatePath;
	}

	public void setErrorTemplatePath(String errorTemplatePath) {
		this.errorTemplatePath = errorTemplatePath;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization errorTemplatePath,and this file must exists.");
		if (StringUtil.isBlank(errorTemplatePath)) {
			throw new MutilsException("Excel 初始化失败,请检查配置文件是否正确.");
		}
		if (!new File(errorTemplatePath).exists()) {
			throw new MutilsException("Excel 初始化失败,错误模板文件:" + errorTemplatePath + " 不存在.");
		}
	}

}
