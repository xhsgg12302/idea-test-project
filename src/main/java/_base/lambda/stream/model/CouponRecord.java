/*
 *
 *  * Copyright (c) 2015-2019  by  安富通科技有限公司 版权所有
 *  * This software is the proprietary information of ANFT Technology.
 *  * BEIJING  ANFT TECHNOLOGY CO.,LTD. All Rights Reserved.
 *  *  project.name: sentrybox
 *  *  module.name: ctsentry
 *
 *
 */

package _base.lambda.stream.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商户下发本地的优惠券记录列表
 * </p>
 *
 * @author abc
 * @since 2019-09-25
 */
public class CouponRecord{

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 优惠劵编号
     */
	private String couponCode;
	/**
	 * 优惠劵名称
	 */
	private String couponName;
	/**
	 * 交易流水号
	 */
	private String tradeNo;
	/**
	 * 订单号（停车记录号）
	 */
	private String orderNum;
	/**
	 * 面值
	 */
	private BigDecimal faceValue;
    /**
     * 商户编号
     */
	private String busCode;
    /**
     * 商户名称
     */
	private String busName;
    /**
     * 优惠券对应的车牌号
     */
	private String carNo;
    /**
     * 是否通用优惠券，0否，1是
     */
	private Integer isGlobal;
    /**
     * 优惠劵种类，1集团用户，2商家发放(限制停车场劵)，3.促销活动
     */
	private Integer clazz;
    /**
     * 0：免费券 1：金额扣减券，车费减免固定金额结算 4：时间券，时间减免后再按车费结算
     */
	private Integer type;
    /**
     * 分钟数，如果优惠劵类型为时间劵，取分钟数的值。
     */
	private Integer minutes;
    /**
     * 金额，如果优惠劵类型为金额劵，取金额的值。
     */
	private BigDecimal amt;
    /**
     * 折扣劵的系数,如0.85为八五折，需保留两位小数
     */
	private BigDecimal discount;
    /**
     * 最终抵扣金额
     */
	private BigDecimal deductAmt;
    /**
     * 优惠券有效开始时间
     */
	private Date startTime;
    /**
     * 优惠券有效开始时间
     */
	private Date endTime;
    /**
     * 0,不可以叠加,1可以叠加
     */
	private String hasCover;
    /**
     * 0,否，1是。
     */
	private Integer hasUse;
    /**
     * 发送类型,1:线上优惠券，2：本地优惠券
     */
	private Integer sendType;
    /**
     * 交易时间 YYYY-MM-dd E HH:mm:ss
     */
	private Date txTime;
    /**
     * 优惠券发送时间
     */
	private Date grantTime;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 操作人
     */
	private String operator;

	public CouponRecord() {
	}

	public CouponRecord(Long id, BigDecimal faceValue, String hasCover,Date endTime) {
		this.id = id;
		this.faceValue = faceValue;
		this.hasCover = hasCover;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponName() {
		return couponName;
	}

	public BigDecimal getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Integer getIsGlobal() {
		return isGlobal;
	}

	public void setIsGlobal(Integer isGlobal) {
		this.isGlobal = isGlobal;
	}

	public Integer getClazz() {
		return clazz;
	}

	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getDeductAmt() {
		return deductAmt;
	}

	public void setDeductAmt(BigDecimal deductAmt) {
		this.deductAmt = deductAmt;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getHasCover() {
		return hasCover;
	}

	public void setHasCover(String hasCover) {
		this.hasCover = hasCover;
	}

	public Integer getHasUse() {
		return hasUse;
	}

	public void setHasUse(Integer hasUse) {
		this.hasUse = hasUse;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public Date getTxTime() {
		return txTime;
	}

	public void setTxTime(Date txTime) {
		this.txTime = txTime;
	}

	public Date getGrantTime() {
		return grantTime;
	}

	public void setGrantTime(Date grantTime) {
		this.grantTime = grantTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "CouponRecord{" +
			"id=" + id +
			", couponCode=" + couponCode +
			", busCode=" + busCode +
			", busName=" + busName +
			", carNo=" + carNo +
			", isGlobal=" + isGlobal +
			", clazz=" + clazz +
			", type=" + type +
			", minutes=" + minutes +
			", amt=" + amt +
			", discount=" + discount +
			", deductAmt=" + deductAmt +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", hasCover=" + hasCover +
			", hasUse=" + hasUse +
			", sendType=" + sendType +
			", txTime=" + txTime +
			", grantTime=" + grantTime +
			", createTime=" + createTime +
			", operator=" + operator +
			"}";
	}
}
