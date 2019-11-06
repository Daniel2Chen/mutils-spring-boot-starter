package cn.minsin.meituan.peisong.model.send;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.tools.NumberUtil;
import cn.minsin.core.web.VO;
import cn.minsin.meituan.peisong.enums.OrderType;

import java.math.BigDecimal;
import java.util.List;

/**
 * 创建订单（门店方式）参数
 */
public class OrderByShopModel extends AbstractMeituanSendModel {

    /**
     *
     */
    private static final long serialVersionUID = -5932552839463795947L;

    @NotNull(key = "delivery_id",
            value = "即配送活动标识，由外部系统生成，不同order_id应对应不同的delivery_id，若因美团系统故障导致发单接口失败，可利用相同的delivery_id重新发单，系统视为同一次配送活动，若更换delivery_id，则系统视为两次独立配送活动。")
    private Long deliveryId;

    @NotNull(key = "order_id",
            value = "订单id，即该订单在合作方系统中的id，最长不超过32个字符 注：目前若某一订单正在配送中（状态不为取消），再次发送同一订单（order_id相同）将返回同一mt_peisong_id")
    private String orderId;

    @NotNull(key = "shop_id",
            value = "取货门店id，即合作方向美团提供的门店id 注：测试门店的shop_id固定为 test_0001 ，仅用于对接时联调测试。")
    private String shopId;

    @NotNull(key = "delivery_service_code",
            value = "配送服务代码，详情见合同 飞速达:4002 快速达:4011 及时达:4012 集中送:4013")
    private Integer deliveryServiceCode;

    @NotNull(key = "receiver_name", value = "收件人名称，最长不超过256个字符")
    private String receiverName;

    @NotNull(key = "receiver_address", value = "收件人地址，最长不超过512个字符")
    private String receiverAddress;

    @NotNull(key = "receiver_phone", value = "收件人电话，最长不超过64个字符")
    private String receiverPhone;

    @NotNull(key = "receiver_lng", value = "收件人经度（默认火星坐标），真实坐标 * 10^6")
    private Integer receiverLng;

    @NotNull(key = "receiver_lat", value = " 收件人纬度（默认火星坐标），真实坐标 * 10^6")
    private Integer receiverLat;

    @NotNull(key = "coordinate_type", value = "收件人坐标类型  0：火星坐标(高德，腾讯地图均采用火星坐标)  1：百度坐标")
    private Integer coordinateType = 0;

    @NotNull(key = "goods_value", value = "货物价格，单位为元，精确到小数点后两位，范围为0-5000")
    private BigDecimal goodsValue;

    @NotNull(key = "goods_height",
            value = "货物高度，单位为cm，精确到小数点后两位，范围为0-45",
            notNull = false)
    private BigDecimal goodsHeight;

    @NotNull(key = "goods_width",
            value = "货物宽度，单位为cm，精确到小数点后两位，范围为0-50",
            notNull = false)
    private BigDecimal goodsWidth;

    @NotNull(key = "goods_length",
            value = "货物长度，单位为cm，精确到小数点后两位，范围为0-65",
            notNull = false)
    private BigDecimal goodsLength;

    @NotNull(key = "goods_weight",
            value = "货物重量，单位为kg，精确到小数点后两位，范围为0-50")
    private BigDecimal goodsWeight;

    @NotNull(key = "goods_detail",
            value = "货物详情，最长不超过10240个字符。 强烈建议提供，方便骑手在取货时确认货品信息",
            notNull = false)
    private String goodsDetail;

    @NotNull(key = "goods_pickup_info",
            value = "货物取货信息，用于骑手到店取货，最长不超过100个字符",
            notNull = false)
    private String goodsPickupInfo;

    @NotNull(key = "goods_delivery_info",
            value = "货物交付信息，最长不超过100个字符",
            notNull = false)
    private String goodsDeliveryInfo;

    @NotNull(key = "expected_pickup_time",
            value = "期望取货时间，时区为GMT+8，当前距离 Epoch（1970年1月1日) 以秒计算的时间，即unix-timestamp。",
            notNull = false)
    private Long expectedPickupTime;

    @NotNull(key = "expected_delivery_time",
            value = "期望送达时间，时区为GMT+8，当前距离Epoch（1970年1月1日) 以秒计算的时间，即unix-timestamp。",
            notNull = false)
    private Long expectedDeliveryTime;

    @NotNull(key = "poi_seq",
            value = "门店订单流水号，格式类似 #1 * 建议提供，方便骑手门店取货",
            notNull = false)
    private String poiSeq;

    @NotNull(key = "note",
            value = "备注，最长不超过200个字符。",
            notNull = false)
    private String note;

    @NotNull(key = "cash_on_delivery",
            value = "骑手应付金额，单位为元，精确到分【预留字段】",
            notNull = false)
    private BigDecimal cashOnDelivery;

    @NotNull(key = "cash_on_pickup",
            value = "骑手应收金额，单位为元，精确到分【预留字段】",
            notNull = false)
    private BigDecimal cashOnPickup;

    @NotNull(value = "发票抬头，最长不超过256个字符【预留字段】", notNull = false)
    private String invoiceTitle;

    @NotNull(key = "order_type",
            value = "订单类型，目前只支持预约单 0: 及时单(尽快送达，限当日订单) 1: 预约单")
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getDeliveryServiceCode() {
        return deliveryServiceCode;
    }

    public void setDeliveryServiceCode(Integer deliveryServiceCode) {
        this.deliveryServiceCode = deliveryServiceCode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
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
        this.goodsValue = NumberUtil.decimalsXLength(2, goodsValue);
    }

    public BigDecimal getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(BigDecimal goodsHeight) {
        this.goodsHeight = NumberUtil.decimalsXLength(2, goodsHeight);
    }

    public BigDecimal getGoodsWidth() {
        return goodsWidth;
    }

    public void setGoodsWidth(BigDecimal goodsWidth) {
        this.goodsWidth = NumberUtil.decimalsXLength(2, goodsWidth);
    }

    public BigDecimal getGoodsLength() {
        return goodsLength;
    }

    public void setGoodsLength(BigDecimal goodsLength) {
        this.goodsLength = NumberUtil.decimalsXLength(2, goodsLength);
    }

    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        this.goodsWeight = NumberUtil.decimalsXLength(2, goodsWeight);
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

    public BigDecimal getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(BigDecimal cashOnDelivery) {
        this.cashOnDelivery = NumberUtil.decimalsXLength(2, cashOnDelivery);
    }

    public BigDecimal getCashOnPickup() {
        return cashOnPickup;
    }

    public void setCashOnPickup(BigDecimal cashOnPickup) {
        this.cashOnPickup = NumberUtil.decimalsXLength(2, cashOnPickup);
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        if (orderType != null) {
            this.orderType = orderType.getCode();
        }
    }

}
