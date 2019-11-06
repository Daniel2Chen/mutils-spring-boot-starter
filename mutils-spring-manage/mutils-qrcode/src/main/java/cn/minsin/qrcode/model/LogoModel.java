package cn.minsin.qrcode.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Getter
@Setter
public class LogoModel extends AbstractModelRule {

    /**
     *
     */
    private static final long serialVersionUID = -4459664046168353683L;

    @NotNull("log宽度 不能大于二维码宽度的1/5 否则会无法识别")
    private Integer width = 100;

    @NotNull("高度 不能大于二维码高度的1/5 否则会无法识别")
    private Integer height = 100;

    @NotNull("logo文件")
    @Setter(AccessLevel.NONE)
    private InputStream logo;

    @NotNull("logo边框颜色 默认白色")
    private Color borderColor = Color.WHITE;

    @NotNull("是否弧形 默认 true")
    private Boolean isArc = true;


    public void setLogo(File file) throws FileNotFoundException {
        this.logo = new FileInputStream(file);
    }

    public void setLogo(FileInputStream fileInputStream) {
        this.logo = fileInputStream;
    }
}
