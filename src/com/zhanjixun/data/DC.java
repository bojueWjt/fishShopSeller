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
	 * �û���¼
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
	 * ��ȡ�̼��Ѿ���������Ʒ
	 * 
	 * @param dataReturnListener
	 * @param sellerId
	 * @param page
	 * @param index
	 */
	public void onSell(OnDataReturnListener dataReturnListener,
			String shopId, int page, int index) {
		// ���ͼ��1������Ʒ.jpg
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopId", shopId);
		params.put("pageInfo.indexPageNum", index + "");
		params.put("pageInfo.size", page + "");
		getDatasFromServer(TaskTag.Category,
				"shop_getCategoryWithTotalNumber.action", params,
				dataReturnListener);
	}

	/**
	 * δ����ģʽ����ȡ�̼����ж���
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
	 * δ����ģʽ����ȡ�̼�δ֧������
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
	 * δ����ģʽ����ȡ�̼�δ����
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
	 * δ����ģʽ����ȡ�̼�δ�ջ�
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
	 * δ����ģʽ����ȡ�̼�δ����
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
	 * ����ģʽ����ȡ���γ�������
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
