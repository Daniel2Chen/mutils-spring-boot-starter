package cn.mutils.meituan.peisong.model.receive;

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

	public static class Location {
		/**
		 * 纬度
		 */
		private Integer lat;

		/**
		 * 经度
		 */
		private Integer lng;

		public Double getLat() {
			return (double) (lat / 1000000);
		}

		public void setLat(Integer lat) {
			this.lat = lat;
		}

		public Double getLng() {
			return (double) (lng / 1000000);
		}

		public void setLng(Integer lng) {
			this.lng = lng;
		}

	}
}
