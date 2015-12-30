package com.zhanjixun.domain;

import java.util.Date;

import android.content.Context;

import com.zhanjixun.data.Constants;
import com.zhanjixun.utils.MyGson;
import com.zhanjixun.utils.SPUtil;

public class User {
	
	private String userId;
	private Integer userType;
	private String identityId;
	private Integer state;
	private String userName;
	private String phoneNumber;
	private String headImage;
	private String getAddressId;
	private Date createTime;

	public boolean saveUserInfo(Context context) {
		String userStr = MyGson.getInstance().toJson(this);
		return SPUtil.saveString(context, Constants.SP_USER, Constants.SP_USER,
				userStr);
	}

	public static User getUserFormSP(Context context) {

		String string = SPUtil.getString(context, Constants.SP_USER,
				Constants.SP_USER, "");

		User user = string.equals("") ? null : MyGson.getInstance().fromJson(
				string, User.class);

		return user;

	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId Ҫ���õ� userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return userType
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType Ҫ���õ� userType
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * @return identityId
	 */
	public String getIdentityId() {
		return identityId;
	}

	/**
	 * @param identityId Ҫ���õ� identityId
	 */
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	/**
	 * @return state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state Ҫ���õ� state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName Ҫ���õ� userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber Ҫ���õ� phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage Ҫ���õ� headImage
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	/**
	 * @return getAddressId
	 */
	public String getGetAddressId() {
		return getAddressId;
	}

	/**
	 * @param getAddressId Ҫ���õ� getAddressId
	 */
	public void setGetAddressId(String getAddressId) {
		this.getAddressId = getAddressId;
	}

	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime Ҫ���õ� createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
