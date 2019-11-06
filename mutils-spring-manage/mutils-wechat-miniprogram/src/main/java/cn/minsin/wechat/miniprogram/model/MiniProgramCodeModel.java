package cn.minsin.wechat.miniprogram.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MiniProgramCodeModel extends AbstractModelRule {

    /**
     *
     */
    private static final long serialVersionUID = 3462772033020881939L;


    @NotNull("最大32个可见字符，只支持数字，大小写英文以及部分特殊字符(!#$&'()*+,/:;=?@-._~)")
    private String scene;

    @NotNull("接口调用凭证")
    private transient String access_token;

    @NotNull("根路径前不要填加 /,必须是已经发布的小程序存在的页面(否则报错)不能携带参数（参数请放在scene字段里）")
    private String page;

    @NotNull("二维码的宽度，单位 px，最小 280px，最大 1280px")
    private Integer width = 280;

    @NotNull("自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false")
    private Boolean auto_color = false;

    @NotNull(value = "auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {\"r\":\"xxx\",\"g\":\"xxx\",\"b\":\"xxx\"} 十进制表示", notNull = false)
    private String line_color;

    @NotNull("是否需要透明底色，为 true 时，生成透明底色的小程序")
    private Boolean is_hyaline = false;


}
