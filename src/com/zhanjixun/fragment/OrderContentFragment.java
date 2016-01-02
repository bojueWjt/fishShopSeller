package com.zhanjixun.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.zhanjixun.R;
import com.zhanjixun.adapter.OrderListAdapter;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain.BaseResult;
import com.zhanjixun.domain.Order;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.MyGson;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;
import com.zhanjixun.views.ReflashListView;
import com.zhanjixun.views.ReflashListView.OnRefreshListener;

public class OrderContentFragment extends Fragment implements
		OnRefreshListener, OnDataReturnListener {
	private int index = 1;
	private static final int page = 7;

	private int tag = 0;
	private ReflashListView mListView;

	private List<Order> orders = new ArrayList<Order>();
	private OrderListAdapter adapter;
	private LoadingDialog dialog;
	private String shopId;
	private MessageDialog msg;

	public OrderContentFragment(int tag) {
		this.tag = tag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_order_content, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle bundle) {
		super.onActivityCreated(bundle);

		msg = new MessageDialog(getActivity());
		dialog = new LoadingDialog(getActivity());
		shopId = Constants.shop.getShopId();
		adapter = new OrderListAdapter(getActivity(), orders);

		initViews();
		loadData();
	}

	private void loadData() {
		// 第一次加载数据显示进度条
		if (index == 1) {
			dialog.show();
		}

		switch (tag) {
		case 0:
			DC.getInstance().getShopAllOrders(this, shopId, page, index++);
			break;
		case 1:
			DC.getInstance().getShopUnPayOrders(this, shopId, page, index++);
			break;
		case 2:
			DC.getInstance().getShopUnSentOrders(this, shopId, page, index++);
			break;
		case 3:
			DC.getInstance().getShopUngetOrders(this, shopId, page, index++);
			break;
		case 4:
			DC.getInstance()
					.getShopUnCommentOrders(this, shopId, page, index++);
			break;
		default:
			break;
		}
	}

	private void initViews() {
		mListView = (ReflashListView) getView().findViewById(
				R.id.fragment_order_content_data);
		mListView.setAdapter(adapter);
		mListView.setOnRefreshListener(this);
	}

	@Override
	public void onLoadingMore(View v) {
		loadData();
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			List<Order> orders = new ArrayList<Order>();
			String ordersKey = result.getResultParam().get("ordersList");
			Type type = new TypeToken<List<Order>>() {
			}.getType();
			orders = MyGson.getInstance().fromJson(ordersKey, type);
			showData(orders);
		} else {
			msg.setMessage(result.getResultInfo()).show();
		}
	}

	private void showData(List<Order> orders) {
		this.orders.addAll(orders);
		adapter.notifyDataSetChanged();
		mListView.hideFooterView();
	}
}
