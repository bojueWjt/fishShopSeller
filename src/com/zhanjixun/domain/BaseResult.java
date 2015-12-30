package com.zhanjixun.domain;

import java.util.Map;

public class BaseResult {
	/**
	 * ���״̬
	 */
	private Boolean serviceResult;
	/**
	 * �����ʾ��Ϣ
	 */
	private String resultInfo;
	/**
	 * �����Ϣ,����������ͬ��
	 */
	private Map<String, String> resultParam;

	/**
	 * @return serviceResult
	 */
	public Boolean getServiceResult() {
		return serviceResult;
	}

	/**
	 * @param serviceResult
	 *            Ҫ���õ� serviceResult
	 */
	public void setServiceResult(Boolean serviceResult) {
		this.serviceResult = serviceResult;
	}

	/**
	 * @return resultInfo
	 */
	public String getResultInfo() {
		return resultInfo;
	}

	/**
	 * @param resultInfo
	 *            Ҫ���õ� resultInfo
	 */
	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	/**
	 * @return resultParm
	 */
	public Map<String, String> getResultParam() {
		return resultParam;
	}

	/**
	 * @param resultParm
	 *            Ҫ���õ� resultParm
	 */
	public void setResultParam(Map<String, String> resultParm) {
		this.resultParam = resultParm;
	}
}
