package org.mutils.qrcode.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;

public class QrcodeModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3139191851191875798L;
	@NotNull("二维码内容")
	private String content;
	
	@NotNull("宽度")
	private Integer width = 500;
	
	@NotNull("高度")
	private Integer height = 500;
	
	@NotNull("图片类型")
	private String format = "png";
	
	@NotNull(value="二维码中logo,文件路径不能包含中文",notNull=false)
	private LogoModel logoImageModel;
	
	@NotNull("二维码白边等级  -1表示没有白边  0 1 2 3 4  白边要依次增多 默认1")
	private int level =1;

	public LogoModel getLogoImageModel() {
		return logoImageModel;
	}
	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public void setLogoImageModel(LogoModel logoImageModel) {
		this.logoImageModel = logoImageModel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}
