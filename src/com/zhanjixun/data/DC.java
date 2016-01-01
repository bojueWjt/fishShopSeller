package com.zhanjixun.data;

import java.util.HashMap;
import java.util.Map;

import com.zhanjixun.interfaces.OnDataReturnListener;

public class DC extends DataCenter {

	private static DC dc = null;

	private DC() {
	}

	public static DC getInstance() {
		if (dc == null) {
			synchronized (DC.class) {
				if (dc == null)
					dc = new DC();
			}
		}
		return dc;
	}

	/**
	 * 用户登录
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param pw
	 */
	public void login(OnDataReturnListener dataReturnListener, String id,
			String pw) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", id);
		params.put("password", pw);
		getDatasFromServer(TaskTag.LOGIN, "user_userLogin.action", params,
				dataReturnListener);
	}

	/**
	 * 获取商家已经发布的商品
	 * 
	 * @param dataReturnListener
	 * @param sellerId
	 * @param page
	 * @param index
	 */
	public void onSell(OnDataReturnListener dataReturnListener,
			String sellerId, int page, int index) {
		// 设计图：1发布商品.jpg
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", sellerId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", page + "");
		getDatasFromServer(TaskTag.LOGIN,
				"shop_getCategoryWithTotalNumber.action", params,
				dataReturnListener);
	}
}
