package com.zhanjixun.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.MainActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.ReflashListView;

public class SailOrderFragment extends Fragment implements OnClickListener {
	private int index = 1;
	private static final int page = 7;

	private TextView sailTv;
	private ReflashListView orderLv;
	private LoadingDialog dialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sail_order, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		dialog = new LoadingDialog(getActivity());
		initViews();
		//initData();
	}

	private void initData() {
		dialog.dismiss();// TODO
		//DC.getInstance().getOrdersWithSeaRecord(this, sellerId, page, index);
	}

	private void initViews() {
		sailTv = (TextView) getView().findViewById(R.id.text_sailorder_outsail);
		sailTv.setOnClickListener(this);
		orderLv = (ReflashListView) getView().findViewById(
				R.id.fragment_sail_order_data);
	}

	@Override
	public void onClick(View v) {
		MainActivity activity = (MainActivity) getActivity();
		activity.setOrderFragment();
	}
}
