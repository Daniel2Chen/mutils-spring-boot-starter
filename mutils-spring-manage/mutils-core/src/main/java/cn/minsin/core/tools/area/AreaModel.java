package cn.minsin.core.tools.area;

import java.util.ArrayList;
import java.util.List;

import cn.minsin.core.rule.AbstractModelRule;

public class AreaModel  extends AbstractModelRule{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5712490286643607391L;
	
	// 唯一标识 国家指定编码,为身份证前几位
	private String adcode;
	// 经度
	private Double lat;
	// 纬度
	private Double lng;
	// 城市名
	private String name;
	// 等级
	private String level;
	// 上级
	private String parent;
	// 子类
	private List<AreaModel> children;


	public String getAdcode() {
		return adcode;
	}


	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}


	public Double getLat() {
		return lat;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}


	public Double getLng() {
		return lng;
	}


	public void setLng(Double lng) {
		this.lng = lng;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public String getParent() {
		return parent;
	}


	public void setParent(String parent) {
		this.parent = parent;
	}


	public List<AreaModel> getChildren() {
		return children;
	}


	public void setChildren(List<AreaModel> children) {
		this.children = children;
	}
	
	public void setChild(AreaModel children) {
		if(this.children==null) {
			this.children = new ArrayList<>();
		}
		this.children.add(children);
	}
}
