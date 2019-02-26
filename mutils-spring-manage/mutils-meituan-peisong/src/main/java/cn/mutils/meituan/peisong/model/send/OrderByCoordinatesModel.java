package cn.mutils.meituan.peisong.model.send;

import java.math.BigDecimal;
import java.util.List;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.tools.NumberUtil;
import cn.minsin.core.web.VO;
import cn.mutils.meituan.peisong.enums.OrderType;

/**
 * 创建订单（门店方式）参数
 */
public class OrderByCoordinatesModel extends AbstractMeituanSendModel {

    /**
     *
     */
    private static final long serialVersionUID = 6104186441133073759L;

    @NotNull(key = "delivery_id",
            value = "即配送活动标识，由外部系统生成，不同order_id应对应 不同的delivery_id，若因美团系统故障导致发单接口失 败，可利用相同的delivery_id重新发单，系统视为同一次 配送活动，若更换delivery_id，则系统视为两次独立配送 活动。")
    private Long deliveryId;

    @NotNull(key = "order_id",
            value = "订单id，即该订单在合作方系统中的id，最长不超过32个字符\n* 注：目前若某一订单正在配送中（状态不为取消），再次发送同一订单 （order_id相同）将返回同一mt_peisong_id")
    private String orderId;

    @NotNull(key = "delivery_service_code",
            value = "配送服务代码，详情见合同 飞速达:4002 快速达:4011 及时达:4012 集中送:4013")
    private Integer deliveryServiceCode;

    @NotNull(key = "pick_up_type", value = "货物取货类型，目前只支持1, 1：客户配送至站点  2：美团自取")
    private Integer pickUpType = 1;

    @NotNull(key = "receiver_name",value="收件人名称，最长不超过256个字符")
    private String receiverName;

    @NotNull(key="receiver_phone",value="收件人电话，最长不超过64个字符")
    private String receiverPhone;

    @NotNull(key="receiver_province",value="收件人地址省")
    private String receiverProvince;

    @NotNull(key="receiver_city",value = "收件人地址市")
    private String receiverCity;

    @NotNull(key="receiver_country",value ="收件人地址区县")
    private String receiverCountry;

    @NotNull(key = "receiver_town",value = "收件人地址街道",notNull = false)
    private String receiverTown;

    @NotNull(key="receiver_detail_address",value = "收件人地址详情")
    private String receiverDetailAddress;

    @NotNull(key="receiver_lng",value = "收件人经度（默认火星坐标），真实坐标 * 10^6")
    private Integer receiverLng;

    @NotNull(key = "receiver_lat",value = "收件人纬度（默认火星坐标），真实坐标 * 10^6")
    private Integer receiverLat;

    @NotNull(key = "coordinate_type",value = "收件人坐标类型  0：火星坐标(高德，腾讯地图均采用火星坐标)  1：百度坐标")
    private Integer coordinateType = 0;

    @NotNull(value = "货物价格，单位为元，精确到小数点后两位，范围为0-5000",key = "goods_value")
    private BigDecimal goodsValue;

    @NotNull(key = "goods_height",value = "货物高度，单位为cm，精确到小数点后两位，范围为0-45", notNull = false)
    private BigDecimal goodsHeight;

    @NotNull(key = "goods_width",value = "货物宽度，单位为cm，精确到小数点后两位，范围为0-50", notNull = false)
    private BigDecimal goodsWidth;

    @NotNull(key = "goods_length",value = "	货物长度，单位为cm，精确到小数点后两位，范围为0-65", notNull = false)
    private BigDecimal goodsLength;

    @NotNull(key = "goods_weight",value = "货物重量，单位为kg，精确到小数点后两位，范围为0-50")
    private BigDecimal goodsWeight;

    @NotNull(key = "goods_detail",value = "货物详情，最长不超过10240个字符。 强烈建议提供，方便骑手在取货时确认货品信息", notNull = false)
    private String goodsDetail;

    @NotNull(key = "goods_pickup_info",value = "货物取货信息，用于骑手到店取货，最长不超过100个字符", notNull = false)
    private String goodsPickupInfo;

    @NotNull(key = "goods_delivery_info",value = "货物交付信息，最长不超过100个字符", notNull = false)
    private String goodsDeliveryInfo;

    @NotNull(key = "expected_pickup_time",value = "期望取货时间，时区为GMT+8，当前距离 Epoch（1970年1月1日) 以秒计算的时间，即unix-timestamp。", notNull = false)
    private Long expectedPickupTime;

    @NotNull(key = "expected_delivery_time",value = "期望送达时间，时区为GMT+8，当前距离Epoch（1970年1月1日) 以秒计算的时间，即unix-timestamp。")
    private Long expectedDeliveryTime;

    @NotNull(key = "poi_seq",value = "门店订单流水号，格式类似 #1 * 建议提供，方便骑手门店取货", notNull = false)
    private String poiSeq;

    @NotNull(key = "note",value = "备注，最长不超过200个字符。", notNull = false)
    private String note;

    @NotNull(key="order_type",value="订单类型，目前只支持预约单 0: 及时单(尽快送达，限当日订单) 1: 预约单")
    private Integer orderType = 0;

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getDeliveryServiceCode() {
        return deliveryServiceCode;
    }

    public void setDeliveryServiceCode(Integer deliveryServiceCode) {
        this.deliveryServiceCode = deliveryServiceCode;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(OrderGood goodsDetail) {
        if (goodsDetail != null) {
            this.goodsDetail = VO.init("goods", goodsDetail).toString();
        }
    }

    public void setGoodsDetail(List<OrderGood> goodsDetail) {
        if (goodsDetail != null) {
            this.goodsDetail = VO.init("goods", goodsDetail).toString();
        }
    }

    public int getPickUpType() {
        return pickUpType;
    }

    public void setPickUpType(int pickUpType) {
        this.pickUpType = pickUpType;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getReceiverTown() {
        return receiverTown;
    }

    public void setReceiverTown(String receiverTown) {
        this.receiverTown = receiverTown;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public Integer getReceiverLng() {
        return receiverLng;
    }

    public void setReceiverLng(Double receiverLng) {
        if (receiverLng != null) {
            this.receiverLng = new BigDecimal(receiverLng * 1000000).intValue();
        }
    }

    public Integer getReceiverLat() {
        return receiverLat;
    }

    public void setReceiverLat(Double receiverLat) {
        if (receiverLat != null) {
            this.receiverLat = new BigDecimal(receiverLat * 1000000).intValue();
        }
    }

    public Integer getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(Integer coordinateType) {
        this.coordinateType = coordinateType;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(BigDecimal goodsValue) {
        this.goodsValue = NumberUtil.keepLenthDecimals(2, goodsValue);
    }

    public BigDecimal getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(BigDecimal goodsHeight) {
        this.goodsHeight = NumberUtil.keepLenthDecimals(2, goodsHeight);
    }

    public BigDecimal getGoodsWidth() {
        return goodsWidth;
    }

    public void setGoodsWidth(BigDecimal goodsWidth) {
        this.goodsWidth = NumberUtil.keepLenthDecimals(2, goodsWidth);
    }

    public BigDecimal getGoodsLength() {
        return goodsLength;
    }

    public void setGoodsLength(BigDecimal goodsLength) {
        this.goodsLength = NumberUtil.keepLenthDecimals(2, goodsLength);
    }

    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        this.goodsWeight = NumberUtil.keepLenthDecimals(2, goodsWeight);
    }

    public String getGoodsPickupInfo() {
        return goodsPickupInfo;
    }

    public void setGoodsPickupInfo(String goodsPickupInfo) {
        this.goodsPickupInfo = goodsPickupInfo;
    }

    public String getGoodsDeliveryInfo() {
        return goodsDeliveryInfo;
    }

    public void setGoodsDeliveryInfo(String goodsDeliveryInfo) {
        this.goodsDeliveryInfo = goodsDeliveryInfo;
    }

    public Long getExpectedPickupTime() {
        return expectedPickupTime;
    }

    public void setExpectedPickupTime(Long expectedPickupTime) {
        this.expectedPickupTime = expectedPickupTime;
    }

    public Long getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(Long expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public String getPoiSeq() {
        return poiSeq;
    }

    public void setPoiSeq(String poiSeq) {
        this.poiSeq = poiSeq;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        if(orderType!=null){
            this.orderType = orderType.getCode();
        }
    }
}
