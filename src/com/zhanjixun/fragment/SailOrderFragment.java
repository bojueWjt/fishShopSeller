package com.zhanjixun.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zhanjixun.R;
import com.zhanjixun.activity.MainActivity;
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

public class SailOrderFragment extends Fragment implements OnClickListener,
		OnDataReturnListener, OnRefreshListener {
	private int index = 1;
	private static final int page = 7;

	private TextView sailTv;
	private ReflashListView mListView;
	private LoadingDialog dialog;
	private String sellerId;
	private List<Order> orders = new ArrayList<Order>();
	private OrderListAdapter adapter;
	private MessageDialog msg;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sail_order, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		dialog = new LoadingDialog(getActivity());
		msg = new MessageDialog(getActivity());
		sellerId = Constants.shop.getShopId();
		adapter = new OrderListAdapter(getActivity(), orders);
		initViews();
		loadData();
	}

	private void loadData() {
		if (index == 1) {
			dialog.show();
		}
		DC.getInstance().getOrdersWithSeaRecord(this, sellerId, page, index++);
	}

	private void initViews() {
		sailTv = (TextView) getView().findViewById(R.id.text_sailorder_outsail);
		sailTv.setOnClickListener(this);
		mListView = (ReflashListView) getView().findViewById(
				R.id.fragment_sail_order_data);
		mListView.setAdapter(adapter);
		mListView.setOnRefreshListener(this);
	}

	@Override
	public void onClick(View v) {
		MainActivity activity = (MainActivity) getActivity();
		activity.setOrderFragment();
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

	@Override
	public void onLoadingMore(View v) {
	}
}
