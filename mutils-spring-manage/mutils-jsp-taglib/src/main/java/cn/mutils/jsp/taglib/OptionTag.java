package cn.mutils.jsp.taglib;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;

public class OptionTag  extends SimpleTagSupport{
	
	
	// 需要循环的列表
    private Object list;
    
    // 选中的id
    private String chooseId;
    
    // option的value属性值
    private String valueAttr;
    
    // option标签中的值
    private String textAttr;

	public String getValueAttr() {
		return valueAttr;
	}

	public void setValueAttr(String valueAttr) {
		this.valueAttr = valueAttr;
	}

	public String getTextAttr() {
		return textAttr;
	}

	public void setTextAttr(String textAttr) {
		this.textAttr = textAttr;
	}

	public String getChooseId() {
		return chooseId;
	}

	public Object getList() {
		return list;
	}
	public void setList(Object list) {
		this.list = list;
	}
	public void setChooseId(String chooseId) {
		this.chooseId = chooseId;
	}

	@Override
	public void doTag() throws JspException, IOException {
		try {
			JspWriter writer = getJspContext().getOut();
			StringBuffer bf = new StringBuffer("<option value=''>请选择</option>");
			if(list!=null) {
				if(list==null|| !(list instanceof Collection)) {
					throw new MutilsException("${list} Could not parse.")  ;
				}
				@SuppressWarnings("unchecked")
				Collection<Object> co= (Collection<Object>) list;
				for (Object object : co) {
					Field idf = object.getClass().getDeclaredField(valueAttr);
					idf.setAccessible(true);
					Field valuef = object.getClass().getDeclaredField(textAttr);
					valuef.setAccessible(true);
					Object object2 = idf.get(object);
					if(StringUtil.isNotBlank(chooseId)&&chooseId.equals(object2.toString())) {
						bf.append("<option selected='selected' value='"+object2+"'>"+valuef.get(object)+"</option>");
						continue;
					}
					bf.append("<option value='"+object2+"'>"+valuef.get(object)+"</option>");
				}
			}
			writer.write(bf.toString());
			writer.flush();
		}catch (Exception e) {
			throw new JspException(e);
		}
	}
}
