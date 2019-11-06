package cn.minsin.meituan.peisong.model.receive;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReceiveRiderLocationModel extends AbstractMeituanReceiveModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7981109767061117914L;

	private Location data = new Location();

	public Location getData() {
		return data;
	}

	public void setData(Location data) {
		this.data = data;
	}

	public static class Location implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1289402520524926448L;

		/**
		 * 纬度
		 */
		private Integer lat;

		/**
		 * 经度
		 */
		private Integer lng;

		public BigDecimal getLat() {
			if (lat != null) {
				return new BigDecimal(lat).divide(new BigDecimal(1000000));
			}
			return null;
		}
		public void setLat(Integer lat) {
			this.lat = lat;
		}
		public BigDecimal getLng() {
			if (lng != null) {
				return new BigDecimal(lng).divide(new BigDecimal(1000000));
			}
			return null;

		}
		public void setLng(Integer lng) {
			this.lng = lng;
		}

	}
}
