package com.zhanjixun.domain;

import java.util.List;

/**
 * ������Ϣ
 * 
 * @author ղ������
 *
 */
public class LogisticsInfo {

	private String message;
	private String nu;
	private Integer ischeck;
	private String com;
	private Integer status;
	private String condition;
	private Integer state;
	private List<Logistics> data;
	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message Ҫ���õ� message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return nu
	 */
	public String getNu() {
		return nu;
	}
	/**
	 * @param nu Ҫ���õ� nu
	 */
	public void setNu(String nu) {
		this.nu = nu;
	}
	/**
	 * @return ischeck
	 */
	public Integer getIscheck() {
		return ischeck;
	}
	/**
	 * @param ischeck Ҫ���õ� ischeck
	 */
	public void setIscheck(Integer ischeck) {
		this.ischeck = ischeck;
	}
	/**
	 * @return com
	 */
	public String getCom() {
		return com;
	}
	/**
	 * @param com Ҫ���õ� com
	 */
	public void setCom(String com) {
		this.com = com;
	}
	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status Ҫ���õ� status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition Ҫ���õ� condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
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
	 * @return data
	 */
	public List<Logistics> getData() {
		return data;
	}
	/**
	 * @param data Ҫ���õ� data
	 */
	public void setData(List<Logistics> data) {
		this.data = data;
	}

}
