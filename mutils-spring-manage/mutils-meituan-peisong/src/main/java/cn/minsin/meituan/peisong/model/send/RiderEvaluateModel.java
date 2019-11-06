package cn.minsin.meituan.peisong.model.send;

import cn.minsin.core.annotation.NotNull;

/**
 * 	评价骑手
 * @author mintonzhang
 * @date 2019年2月19日
 * @since 0.3.4
 */
public class RiderEvaluateModel extends AbstractMeituanSendModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1493956906997609612L;
	
	@NotNull(key="delivery_id",value="配送活动标识")
	private Long deliveryId;
	
	@NotNull(key="mt_peisong_id",value="美团配送内部订单id，最长不超过32个字符")
	private String mtPeisongId;
	
	@NotNull("评分（5分制） 预留参数，不作为骑手反馈参考 合作方需传入0-5之间分数或者不传，否则报错")
	private Integer score =0;
	
	@NotNull(key="comment_content",value="评论内容( 评论的字符长度需小于1024)")
	private String commentContent;

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getMtPeisongId() {
		return mtPeisongId;
	}

	public void setMtPeisongId(String mtPeisongId) {
		this.mtPeisongId = mtPeisongId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
}
