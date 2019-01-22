package org.mutils.qrcode.model;

import java.io.OutputStream;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.ModelRule;

public class QrcodeModel extends ModelRule {

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
	
	@NotNull("输出对象  可以是HttpServletResponse中的OutPutStream对象 http中设置输出的文件名及类型需要在加载完输出流后设置,也可以是FileOutPutStream对象")
	private OutputStream outputStream;

	
	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public LogoModel getLogoImageModel() {
		return logoImageModel;
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


	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
