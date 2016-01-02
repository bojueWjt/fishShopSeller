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
			String shopId, int page, int index) {
		// 设计图：1发布商品.jpg
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", shopId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", page + "");
		getDatasFromServer(TaskTag.Category,
				"shop_getCategoryWithTotalNumber.action", params,
				dataReturnListener);
	}

	/**
	 * 未出航模式，获取商家所有订单
	 * 
	 * @param dataReturnListener
	 * @param sellerId
	 * @param page
	 * @param index
	 */
	public void getShopAllOrders(OnDataReturnListener dataReturnListener,
			String sellerId, int page, int index) {
		// orders_getMyShopOrders.action
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", sellerId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", page + "");
		getDatasFromServer(TaskTag.MyShop, "orders_getMyShopOrders.action",
				params, dataReturnListener);
	}

	/**
	 * 未出航模式，获取商家未支付订单
	 * 
	 * @param dataReturnListener
	 * @param sellerId
	 * @param page
	 * @param index
	 */
	public void getShopUnPayOrders(OnDataReturnListener dataReturnListener,
			String sellerId, int page, int index) {
		// orders_getShopUnPayOrders.action
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", sellerId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", page + "");
		getDatasFromServer(TaskTag.UnPay, "orders_getShopUnPayOrders.action",
				params, dataReturnListener);
	}

	/**
	 * 未出航模式，获取商家未发货
	 * 
	 * @param dataReturnListener
	 * @param sellerId
	 * @param page
	 * @param index
	 */
	public void getShopUnSentOrders(OnDataReturnListener dataReturnListener,
			String sellerId, int page, int index) {
		// orders_getShopUnSentOrders.action
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", sellerId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", page + "");
		getDatasFromServer(TaskTag.UnSent, "orders_getShopUnSentOrders.action",
				params, dataReturnListener);
	}

	/**
	 * 未出航模式，获取商家未收货
	 * 
	 * @param dataReturnListener
	 * @param sellerId
	 * @param page
	 * @param index
	 */
	public void getShopUngetOrders(OnDataReturnListener dataReturnListener,
			String sellerId, int page, int index) {
		// orders_getShopUngetOrders.action
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", sellerId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", page + "");
		getDatasFromServer(TaskTag.Unget, "orders_getShopUngetOrders.action",
				params, dataReturnListener);
	}

	/**
	 * 未出航模式，获取商家未评论
	 * 
	 * @param dataReturnListener
	 * @param sellerId
	 * @param page
	 * @param index
	 */
	public void getShopUnCommentOrders(OnDataReturnListener dataReturnListener,
			String sellerId, int page, int index) {
		// orders_getShopUnCommentOrders.action
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", sellerId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", page + "");
		getDatasFromServer(TaskTag.UnComment,
				"orders_getShopUnCommentOrders.action", params,
				dataReturnListener);
	}

	/**
	 * 航行模式，获取本次出航订单
	 * 
	 * @param dataReturnListener
	 * @param sellerId
	 * @param page
	 * @param index
	 */
	public void getOrdersWithSeaRecord(OnDataReturnListener dataReturnListener,
			String sellerId, int page, int index) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", sellerId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", page + "");
		getDatasFromServer(TaskTag.SeaRecord,
				"orders_getOrdersWithSeaRecord.action", params,
				dataReturnListener);
	}
}
