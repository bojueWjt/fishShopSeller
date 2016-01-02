package com.zhanjixun.domain;

import java.util.Date;
import java.util.List;

public class Order {
	public final static int state_finish = 4;
	public final static int state_un_commet = 3;
	public final static int state_un_get = 2;
	public final static int state_un_pay = 1;
	public final static int state_un_sent = 0;

	public final static String[] STATE_STR = { "���ѷ���", "ȥ����", "�鿴����", "ȥ����",
			"�����" };

	private String ordersId;
	private String phone;
	private Double postagePrice;
	private String sendAddress;
	private String shopId;
	private String shopKeeperName;
	private Integer state;
	private Double totalprice;
	private String userName;
	private Date createTime;
	private List<Good> ordersDetail;
	private String version;
	private Boolean isDelete;
	private String userId;
	private String buyerName;

	/**
	 * @return ordersId
	 */
	public String getOrdersId() {
		return ordersId;
	}

	/**
	 * @param ordersId
	 *            Ҫ���õ� ordersId
	 */
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            Ҫ���õ� phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return postagePrice
	 */
	public Double getPostagePrice() {
		return postagePrice;
	}

	/**
	 * @param postagePrice
	 *            Ҫ���õ� postagePrice
	 */
	public void setPostagePrice(Double postagePrice) {
		this.postagePrice = postagePrice;
	}

	/**
	 * @return sendAddress
	 */
	public String getSendAddress() {
		return sendAddress;
	}

	/**
	 * @param sendAddress
	 *            Ҫ���õ� sendAddress
	 */
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	/**
	 * @return shopId
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * @param shopId
	 *            Ҫ���õ� shopId
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * @return shopKeeperName
	 */
	public String getShopKeeperName() {
		return shopKeeperName;
	}

	/**
	 * @param shopKeeperName
	 *            Ҫ���õ� shopKeeperName
	 */
	public void setShopKeeperName(String shopKeeperName) {
		this.shopKeeperName = shopKeeperName;
	}

	/**
	 * @return state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state
	 *            Ҫ���õ� state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return totalprice
	 */
	public Double getTotalprice() {
		return totalprice;
	}

	/**
	 * @param totalprice
	 *            Ҫ���õ� totalprice
	 */
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            Ҫ���õ� userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            Ҫ���õ� createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return ordersDetail
	 */
	public List<Good> getOrdersDetail() {
		return ordersDetail;
	}

	/**
	 * @param ordersDetail
	 *            Ҫ���õ� ordersDetail
	 */
	public void setOrdersDetail(List<Good> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}

	/**
	 * @return version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            Ҫ���õ� version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return isDelete
	 */
	public Boolean getIsDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete
	 *            Ҫ���õ� isDelete
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            Ҫ���õ� userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return buyerName
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * @param buyerName
	 *            Ҫ���õ� buyerName
	 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
}
