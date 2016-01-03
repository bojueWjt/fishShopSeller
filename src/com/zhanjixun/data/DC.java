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
	public void onSell(OnDataReturnListener dataReturnListener, String shopId,
			int page, int index) {
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

	/**
	 * ��ȡ������Ϣ
	 * 
	 * @param dataReturnListener
	 * @param orderId
	 */
	public void getOrderInfo(OnDataReturnListener dataReturnListener,
			String orderId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ordersId", orderId);
		getDatasFromServer(TaskTag.ORDER_INFO, "orders_getPointOrders.action",
				params, dataReturnListener);
	}

	/**
	 * ��д������ݵ���
	 * 
	 * @param dataReturnListener
	 * @param orderId
	 * @param orderNumber
	 */
	public void setOrderExpressNum(OnDataReturnListener dataReturnListener,
			String orderId, String orderNumber) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ordersId", orderId);
		params.put("orderNumber", orderNumber);
		getDatasFromServer(TaskTag.setOrderExpressNum,
				"orders_setOrderNumber.action", params, dataReturnListener);
	}

	/**
	 * ��ȡ�����Ŀ�ݺ�
	 * 
	 * @param dataReturnListener
	 * @param ordersId
	 */
	public void orderPostCode(OnDataReturnListener dataReturnListener,
			String ordersId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ordersId", ordersId);
		getDatasFromServer(TaskTag.ORDER_POST_CODE,
				"orders_getOrderNumber.action", params, dataReturnListener);
	}

	/**
	 * ��ȡ������Ϣ
	 * 
	 * @param dataReturnListener
	 * @param postid
	 * @param type
	 */
	public void getLogistic(OnDataReturnListener dataReturnListener,
			String postid) {
		// http://www.kuaidi100.com/query?type=zhongtong&postid=719121392152
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", "zhongtong");
		params.put("postid", postid);
		getLogistics(TaskTag.LOGISTIC, params, dataReturnListener);
	}
}
