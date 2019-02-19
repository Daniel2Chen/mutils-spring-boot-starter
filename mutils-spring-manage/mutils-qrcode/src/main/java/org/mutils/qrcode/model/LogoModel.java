package org.mutils.qrcode.model;

import java.awt.Color;
import java.io.File;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;

public class LogoModel extends AbstractModelRule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4459664046168353683L;
	
	@NotNull("log宽度 不能大于二维码宽度的1/5 否则会无法识别")
	private   Integer width = 100;
	@NotNull("高度 不能大于二维码高度的1/5 否则会无法识别")
	private   Integer height = 100;
	@NotNull("logo文件")
	private File logo;
	@NotNull("logo边框颜色 默认白色")
	private Color borderColor = Color.WHITE;
	
	@NotNull("是否弧形 默认 true")
	private Boolean isArc = true; 
	
	public Boolean getIsArc() {
		return isArc;
	}
	public void setIsArc(Boolean isArc) {
		this.isArc = isArc;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public File getLogo() {
		return logo;
	}
	public void setLogo(File logo) {
		this.logo = logo;
	}
	

}
