package com.zhanjixun.domain;

import java.util.Date;

import android.content.Context;

import com.zhanjixun.data.Constants;
import com.zhanjixun.utils.MyGson;
import com.zhanjixun.utils.SPUtil;

/**
 * �̵�
 * 
 * @author ղ������
 *
 */
public class Shop {
	private String shopId;
	private String shopName;
	private String shopPhoto;
	private String grade;
	private String userId;
	private Float weightQuality;
	private Float freshQuality;
	private Float speedQuality;
	private Integer shopType;
	private Float sendPrice;
	private Boolean onSell;
	private String address;
	private Float longitude;
	private Float latitude;
	private Date createTime;
	private String version;
	private Boolean isDelete;

	/**
	 * "getType": "[4]"
	 */
	/**
	 * "addresscode": "1",
	 */
	/**
	 * "seaRecordId": 1
	 */

	public boolean saveInstance(Context context) {
		String spString = MyGson.getInstance().toJson(this);
		return SPUtil.saveString(context, Constants.SP_SHOP, Constants.SP_SHOP,
				spString);
	}

	public static Shop getInstance(Context context) {
		String defValue = "";
		String string = SPUtil.getString(context, Constants.SP_SHOP,
				Constants.SP_SHOP, defValue);
		Shop bean = string.equals(defValue) ? null : MyGson.getInstance()
				.fromJson(string, Shop.class);
		return bean;
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
	 * @return shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @param shopName
	 *            Ҫ���õ� shopName
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * @return shopPhoto
	 */
	public String getShopPhoto() {
		return shopPhoto;
	}

	/**
	 * @param shopPhoto
	 *            Ҫ���õ� shopPhoto
	 */
	public void setShopPhoto(String shopPhoto) {
		this.shopPhoto = shopPhoto;
	}

	/**
	 * @return grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            Ҫ���õ� grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
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
	 * @return weightQuality
	 */
	public Float getWeightQuality() {
		return weightQuality;
	}

	/**
	 * @param weightQuality
	 *            Ҫ���õ� weightQuality
	 */
	public void setWeightQuality(Float weightQuality) {
		this.weightQuality = weightQuality;
	}

	/**
	 * @return freshQuality
	 */
	public Float getFreshQuality() {
		return freshQuality;
	}

	/**
	 * @param freshQuality
	 *            Ҫ���õ� freshQuality
	 */
	public void setFreshQuality(Float freshQuality) {
		this.freshQuality = freshQuality;
	}

	/**
	 * @return speedQuality
	 */
	public Float getSpeedQuality() {
		return speedQuality;
	}

	/**
	 * @param speedQuality
	 *            Ҫ���õ� speedQuality
	 */
	public void setSpeedQuality(Float speedQuality) {
		this.speedQuality = speedQuality;
	}

	/**
	 * @return shopType
	 */
	public Integer getShopType() {
		return shopType;
	}

	/**
	 * @param shopType
	 *            Ҫ���õ� shopType
	 */
	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	/**
	 * @return sendPrice
	 */
	public Float getSendPrice() {
		return sendPrice;
	}

	/**
	 * @param sendPrice
	 *            Ҫ���õ� sendPrice
	 */
	public void setSendPrice(Float sendPrice) {
		this.sendPrice = sendPrice;
	}

	/**
	 * @return onSell
	 */
	public Boolean getOnSell() {
		return onSell;
	}

	/**
	 * @param onSell
	 *            Ҫ���õ� onSell
	 */
	public void setOnSell(Boolean onSell) {
		this.onSell = onSell;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            Ҫ���õ� address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return longitude
	 */
	public Float getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            Ҫ���õ� longitude
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return latitude
	 */
	public Float getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            Ҫ���õ� latitude
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
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

}
