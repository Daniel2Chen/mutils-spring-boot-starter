package cn.minsin.alipay.enums;

/**
 * 花呗分期选项
 * @author mintonzhang
 * @date 2019年1月10日
 */
public enum HuaBeiStaging {
	
	/**
	 * 3期
	 */
	HB_3(3),
	/**
	 * 6期
	 */
	HB_6(6),
	/**
	 * 12期
	 */
	HB_12(12);
	
	private int stag;

	private HuaBeiStaging(int stag) {
		this.stag = stag;
	}

	public int getStag() {
		return stag;
	}

	public void setStag(int stag) {
		this.stag = stag;
	}

}
