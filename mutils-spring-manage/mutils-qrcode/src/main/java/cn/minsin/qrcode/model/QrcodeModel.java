package cn.minsin.qrcode.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
